import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service'; // Assicurati che il percorso sia corretto

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;

      this.authService.login(email, password).subscribe({
        next: (response) => {
          localStorage.setItem('token', response.token);

          console.log('Login successful! Token:', response.token);

          const userRoles = this.authService.getRolesFromToken();

          if (userRoles.includes('ROLE_SUPERADMIN')) {
            this.router.navigate(['/dashboard-superadmin']); // Esempio per Super Admin
          } else if (userRoles.includes('ROLE_ADMIN')) {
            this.router.navigate(['/dashboard-admin']);     // Esempio per Admin
          } else if (userRoles.includes('ROLE_USER')) {
            this.router.navigate(['/dashboard-apicoltore']); // Esempio per utente normale/apicoltore
          } else {
            this.router.navigate(['/dashboard']);
          }
        },
        error: (err) => {
          console.error('Login failed:', err);
          alert('Credenziali non valide. Riprova.');
        }
      });
    } else {
      console.error('Form non valido. Controlla i campi.');
      alert('Per favore, inserisci un\'email valida e una password.');
    }
  }
}