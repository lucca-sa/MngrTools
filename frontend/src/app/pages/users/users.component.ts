import { Component } from '@angular/core';
import { UsersTableComponent } from './users-table/users-table.component';
import { MatButtonModule } from '@angular/material/button';
import { UserCreateUpdateModalComponent } from './user-create-update-modal/user-create-update-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { UserNotificationService } from '../../shared/services/user-notification.service';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    UsersTableComponent,
    MatButtonModule,
    UserCreateUpdateModalComponent,
  ],
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent {
  constructor(
    private dialog: MatDialog,
    private userNotificationService: UserNotificationService
  ) {}

  openCreateUserModal(): void {
    this.dialog
      .open(UserCreateUpdateModalComponent, {
        width: '400px',
      })
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          this.userNotificationService.notifyUserCreated();
        }
      });
  }
}
