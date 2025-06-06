import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Admin {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrlUsers = 'http://localhost:8082/api/users';
  private apiUrlAuth = 'http://localhost:8082/api/auth';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  /** Restituisce la lista di tutti gli utenti (o admin) */
  getAllAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.apiUrlUsers}`, { headers: this.getAuthHeaders() });
  }

  /** Crea un nuovo admin */
  createAdmin(adminData: { email: string, password: string, firstName: string, lastName: string }): Observable<void> {
    return this.http.post<void>(`${this.apiUrlAuth}/create-admin`, adminData, { headers: this.getAuthHeaders() });
  }

  /** Elimina un admin (o utente generico) */
  deleteAdmin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrlUsers}/${id}`, { headers: this.getAuthHeaders() });
  }
}
