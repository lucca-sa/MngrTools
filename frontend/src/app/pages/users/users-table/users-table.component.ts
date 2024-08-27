import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { PaginatedResponse } from '../../../shared/models/paginated-response.model';
import { User } from '../../../shared/models/user.model';
import { UsersService } from '../../../shared/services/users.service';

@Component({
  selector: 'app-users-table',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, CommonModule],
  templateUrl: './users-table.component.html',
  styleUrl: './users-table.component.scss',
})
export class UsersTableComponent {
  displayedColumns: string[] = [
    'id',
    'name',
    'email',
    'phone',
    'address',
    'departmentName',
  ];
  dataSource = new MatTableDataSource<User>();
  totalItems = 0;
  pageSize = 5;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(page: number = 0, size: number = 5): void {
    this.usersService
      .getUsers(page, size)
      .subscribe((response: PaginatedResponse<User>) => {
        this.dataSource.data = response.data;
        this.totalItems = response.totalItems;

        if (this.paginator) {
          this.paginator.pageIndex = page;
          this.paginator.pageSize = size;
          this.paginator.length = this.totalItems;
        }
      });
  }

  onPageChange(event: any): void {
    const pageIndex = event.pageIndex;
    const pageSize = event.pageSize;
    this.loadUsers(pageIndex, pageSize);
  }
}
