import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserNotificationService {
  private userCreatedSource = new Subject<void>();
  userCreated$ = this.userCreatedSource.asObservable();

  notifyUserCreated(): void {
    this.userCreatedSource.next();
  }
}