import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../models/department.model';

@Injectable({
  providedIn: 'root',
})
export class DepartmentsService {
  private departmentsUrl = 'http://localhost:8080/api/department';

  constructor(private http: HttpClient) {}

  getDepartmentById(departmentId: number): Observable<Department> {
    return this.http.get<Department>(`${this.departmentsUrl}/${departmentId}`);
  }
}
