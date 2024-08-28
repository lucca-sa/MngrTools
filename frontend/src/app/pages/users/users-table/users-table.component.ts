import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { PaginatedResponse } from '../../../shared/models/paginated-response.model';
import { User } from '../../../shared/models/user.model';
import { UsersService } from '../../../shared/services/users.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ApiError } from '../../../shared/models/api-error.model';
import { OnDestroy } from '@angular/core';
import { Subject, debounceTime, takeUntil } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { UserCreateUpdateModalComponent } from '../user-create-update-modal/user-create-update-modal.component';
import { MatButtonModule } from '@angular/material/button';
import { UserNotificationService } from '../../../shared/services/user-notification.service';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../../shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-users-table',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    ConfirmDialogComponent
  ],
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.scss'],
})
export class UsersTableComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = [
    'id',
    'name',
    'email',
    'phone',
    'address',
    'departmentName',
    'actions',
  ];
  dataSource = new MatTableDataSource<User>();
  totalItems = 0;
  pageSize = 5;

  filters = {
    userId: null as number | null,
    userName: '',
    departmentName: '',
  };

  private filterSubject = new Subject<void>();
  private debounceTimeMs = 300;
  private destroy$ = new Subject<void>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private usersService: UsersService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private userNotificationService: UserNotificationService
  ) {}

  ngOnInit(): void {
    this.loadUsers();

    this.userNotificationService.userCreated$
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadUsers(this.paginator.pageIndex, this.paginator.pageSize);
      });

    this.filterSubject
      .pipe(debounceTime(this.debounceTimeMs), takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadUsers();
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadUsers(page: number = 0, size: number = 5): void {
    this.usersService
      .getUsers(
        page,
        size,
        this.filters.userId !== null ? this.filters.userId : undefined,
        this.filters.userName,
        this.filters.departmentName
      )
      .subscribe({
        next: (response: PaginatedResponse<User>) => {
          this.dataSource.data = response.data.sort((a, b) => a.id - b.id);
          this.totalItems = response.totalItems;

          if (this.paginator) {
            this.paginator.pageIndex = page;
            this.paginator.pageSize = size;
            this.paginator.length = this.totalItems;
          }
        },
        error: (err: ApiError) => {
          this.dataSource.data = [];
          this.totalItems = 0;
          if (this.paginator) {
            this.paginator.pageIndex = 0;
            this.paginator.pageSize = size;
            this.paginator.length = this.totalItems;
          }
        },
      });
  }

  applyFilters(): void {
    this.filterSubject.next();
  }

  onPageChange(event: any): void {
    const pageIndex = event.pageIndex;
    const pageSize = event.pageSize;
    this.loadUsers(pageIndex, pageSize);
  }

  openEditModal(userId: number): void {
    const dialogRef = this.dialog.open(UserCreateUpdateModalComponent, {
      data: { userId },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadUsers(this.paginator.pageIndex, this.paginator.pageSize);
      }
    });
  }

  confirmDelete(userId: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        message: 'Are you sure you want to delete this user?'
      },
      autoFocus: false
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteUser(userId);
      }
    });
  }
  
  private deleteUser(userId: number): void {
    this.usersService.deleteUser(userId).subscribe({
      next: () => {
        this.snackBar.open('User deleted successfully', 'Close', { duration: 3000 });
        this.loadUsers(this.paginator.pageIndex, this.paginator.pageSize);
      },
      error: () => {
        this.snackBar.open('Failed to delete user', 'Close', { duration: 3000 });
      }
    });
  }
}
