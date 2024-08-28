import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Department,
  DepartmentDto,
  DepartmentItem,
} from '../models/department.model';
import { PaginatedResponse } from '../models/paginated-response.model';

@Injectable({
  providedIn: 'root',
})
export class DepartmentsService {
  private departmentsUrl = 'http://localhost:8080/api/department';

  constructor(private http: HttpClient) {}

  createDepartment(department: DepartmentDto): Observable<Department> {
    return this.http.post<Department>(this.departmentsUrl, department);
  }

  getDepartmentById(departmentId: number): Observable<Department> {
    return this.http.get<Department>(`${this.departmentsUrl}/${departmentId}`);
  }

  getDepartments(
    page: number,
    size: number,
    departmentId?: number,
    departmentName?: string
  ): Observable<PaginatedResponse<DepartmentItem>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (departmentId !== undefined) {
      params = params.set('departmentId', departmentId.toString());
    }
    if (departmentName !== undefined) {
      params = params.set('departmentName', departmentName);
    }

    return this.http.get<PaginatedResponse<DepartmentItem>>(
      this.departmentsUrl,
      {
        params,
      }
    );
  }

  updateDepartment(
    departmentId: number,
    departmentDto: DepartmentDto
  ): Observable<Department> {
    return this.http.patch<Department>(
      `${this.departmentsUrl}/${departmentId}`,
      departmentDto
    );
  }

  deleteDepartment(departmentId: number): Observable<void> {
    return this.http.delete<void>(`${this.departmentsUrl}/${departmentId}`);
  }
}
