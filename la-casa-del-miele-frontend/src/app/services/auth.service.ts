import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/auth';  

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<{ token: string }> {
    const body = { email, password };
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, body);
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getRolesFromToken(): string[] {
    const token = this.getToken();
    if (!token) {
      return [];
    }

    try { 
      const payloadBase64 = token.split('.')[1];
      const decodedPayload = JSON.parse(atob(payloadBase64));

      console.log("Decoded JWT payload:", decodedPayload);

      if (decodedPayload && decodedPayload.scope) {
        return decodedPayload.scope.split(' ');
      }

      return [];
    } catch (e) {
      console.error("Error decoding JWT or extracting roles:", e);
      return [];
    }
  }
}
