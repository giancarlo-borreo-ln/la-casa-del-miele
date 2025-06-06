import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard-superadmin', 
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-superadmin.component.html',
  styleUrls: ['./dashboard-superadmin.component.css']
})
export class DashboardSuperadminComponent implements OnInit {
  admins: any[] = []; 

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.loadAdmins();
  }

  loadAdmins(): void {
    this.adminService.getAllAdmins().subscribe({
      next: (data) => {
        this.admins = data;
      },
      error: (error) => {
        console.error('Errore nel caricamento degli admin:', error);
        alert('Errore nel caricamento degli admin');
      }
    });
  }

  editAdmin(admin: any): void {
    // Puoi implementare una rotta per la modifica, per ora mettiamo un log
    console.log('Modifica admin:', admin);
    alert(`Funzionalità di modifica in lavorazione per ${admin.nome}`);
  }

  deleteAdmin(adminId: number): void {
    if (confirm('Sei sicuro di voler eliminare questo admin?')) {
      this.adminService.deleteAdmin(adminId).subscribe({
        next: () => {
          alert('Admin eliminato con successo');
          this.loadAdmins(); 
        },
        error: (error) => {
          console.error('Errore nell\'eliminazione dell\'admin:', error);
          alert('Errore nell\'eliminazione');
        }
      });
    }
  }
}
