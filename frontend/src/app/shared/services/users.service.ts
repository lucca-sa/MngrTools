import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaginatedResponse } from '../models/paginated-response.model';
import { User, UserDto } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private usersUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {}

  createUser(user: UserDto): Observable<User> {
    return this.http.post<User>(this.usersUrl, user);
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.usersUrl}/${userId}`);
  }

  getUsers(
    page: number,
    size: number,
    userId?: number,
    userName?: string,
    departmentName?: string,
    departmentId?: number
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
    if (departmentId !== undefined) {
      params = params.set('departmentId', departmentId);
    }

    return this.http.get<PaginatedResponse<User>>(this.usersUrl, { params });
  }

  updateUser(userId: number, userDto: UserDto): Observable<User> {
    return this.http.patch<User>(`${this.usersUrl}/${userId}`, userDto);
  }

  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.usersUrl}/${userId}`);
  }
}
