import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DepartmentNotificationService {
  private departmentCreatedSource = new Subject<void>();
  departmentCreated$ = this.departmentCreatedSource.asObservable();

  notifyDepartmentCreated(): void {
    this.departmentCreatedSource.next();
  }
}