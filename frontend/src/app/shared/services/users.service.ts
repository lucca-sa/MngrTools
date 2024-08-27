import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaginatedResponse } from '../models/paginated-response.model';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private usersUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {}

  getUsers(
    page: number,
    size: number,
    userId?: number,
    userName?: string,
    departmentName?: string
  ): Observable<PaginatedResponse<User>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (userId !== undefined) {
      params = params.set('userId', userId.toString());
    }
    if (userName !== undefined) {
      params = params.set('userName', userName);
    }
    if (departmentName !== undefined) {
      params = params.set('departmentName', departmentName);
    }

    return this.http.get<PaginatedResponse<User>>(this.usersUrl, { params });
  }
}
