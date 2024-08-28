import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { UserCreateUpdateModalComponent } from './user-create-update-modal/user-create-update-modal.component';
import { UserNotificationService } from '../../shared/services/user-notification.service';
import { UsersTableComponent } from './users-table/users-table.component';

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
export class UsersComponent implements OnInit {
  departmentId: number | null = null;

  constructor(
    private dialog: MatDialog,
    private userNotificationService: UserNotificationService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.departmentId = params['departmentId']
        ? +params['departmentId']
        : null;
    });
  }

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
