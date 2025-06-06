import { Routes } from '@angular/router';
import { AuthGuard } from './services/auth.guard'; 

import { ApiariFormComponent } from './apiari-form/apiari-form.component';
import { ApiariListComponent } from './apiari-list/apiari-list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardAdminComponent } from './dashboard-admin/dashboard-admin.component';
import { DashboardApicoltoreComponent } from './dashboard-apicoltore/dashboard-apicoltore.component';
import { DashboardSuperadminComponent } from './dashboard-superadmin/dashboard-superadmin.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent, canActivate: [AuthGuard] }, 
  { path: 'login', component: LoginComponent },

  { path: 'dashboard-apicoltore', component: DashboardApicoltoreComponent, canActivate: [AuthGuard] },
  { path: 'dashboard-admin', component: DashboardAdminComponent, canActivate: [AuthGuard] },
  { path: 'dashboard-superadmin', component: DashboardSuperadminComponent, canActivate: [AuthGuard] },

  { path: 'apiari-form', component: ApiariFormComponent, canActivate: [AuthGuard] },
  { path: 'apiari-list', component: ApiariListComponent, canActivate: [AuthGuard] },

  { path: '**', redirectTo: '' }
];
