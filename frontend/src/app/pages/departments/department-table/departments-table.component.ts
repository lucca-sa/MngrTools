import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { PaginatedResponse } from '../../../shared/models/paginated-response.model';
import {
  Department,
  DepartmentItem,
} from '../../../shared/models/department.model';
import { DepartmentsService } from '../../../shared/services/departments.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ApiError } from '../../../shared/models/api-error.model';
import { Subject, debounceTime, takeUntil } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { DepartmentCreateUpdateModalComponent } from '../department-create-update-modal/department-create-update-modal.component';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../../shared/components/confirm-dialog/confirm-dialog.component';
import { DepartmentNotificationService } from '../../../shared/services/department-notification.service';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router } from '@angular/router';

@Component({
  selector: 'app-departments-table',
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
    ConfirmDialogComponent,
    MatTooltipModule,
  ],
  templateUrl: './departments-table.component.html',
  styleUrls: ['./departments-table.component.scss'],
})
export class DepartmentsTableComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['id', 'name', 'actions'];
  dataSource = new MatTableDataSource<Department>();
  totalItems = 0;
  pageSize = 5;

  filters = {
    departmentId: null as number | null,
    departmentName: '',
  };

  private filterSubject = new Subject<void>();
  private debounceTimeMs = 300;
  private destroy$ = new Subject<void>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private departmentsService: DepartmentsService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private departmentNotificationService: DepartmentNotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadDepartments();

    this.departmentNotificationService.departmentCreated$
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadDepartments(this.paginator.pageIndex, this.paginator.pageSize);
      });

    this.filterSubject
      .pipe(debounceTime(this.debounceTimeMs), takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadDepartments();
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadDepartments(page: number = 0, size: number = 5): void {
    this.departmentsService
      .getDepartments(
        page,
        size,
        this.filters.departmentId !== null
          ? this.filters.departmentId
          : undefined,
        this.filters.departmentName
      )
      .subscribe({
        next: (response: PaginatedResponse<DepartmentItem>) => {
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
    this.loadDepartments(pageIndex, pageSize);
  }

  openEditModal(departmentId: number): void {
    const dialogRef = this.dialog.open(DepartmentCreateUpdateModalComponent, {
      data: { departmentId },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadDepartments(this.paginator.pageIndex, this.paginator.pageSize);
      }
    });
  }

  viewUsers(departmentId: number): void {
    this.router.navigate(['/users'], { queryParams: { departmentId } });
  }

  confirmDelete(departmentId: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        message: 'Are you sure you want to delete this department?',
      },
      autoFocus: false,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.deleteDepartment(departmentId);
      }
    });
  }

  private deleteDepartment(departmentId: number): void {
    this.departmentsService.deleteDepartment(departmentId).subscribe({
      next: () => {
        this.snackBar.open('Department deleted successfully', 'Close', {
          duration: 3000,
        });
        this.loadDepartments(this.paginator.pageIndex, this.paginator.pageSize);
      },
      error: (err: ApiError) => {
        this.snackBar.open('Failed to delete department', 'Close', {
          duration: 3000,
        });
      },
    });
  }

  clearFilters() {
    this.filters = {
      departmentId: null,
      departmentName: '',
    };

    this.loadDepartments();
  }
}
