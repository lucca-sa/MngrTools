import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'users',
    loadComponent: () =>
      import('./pages/users/users.component').then((m) => m.UsersComponent),
  },
  {
    path: 'departments',
    loadComponent: () =>
      import('./pages/departments/departments.component').then(
        (m) => m.DepartmentsComponent
      ),
  },
  {
    path: '',
    redirectTo: 'users',
    pathMatch: 'full'
  }
];
