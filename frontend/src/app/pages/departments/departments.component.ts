import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { DepartmentsTableComponent } from './department-table/departments-table.component';
import { DepartmentCreateUpdateModalComponent } from './department-create-update-modal/department-create-update-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { DepartmentNotificationService } from '../../shared/services/department-notification.service';

@Component({
  selector: 'app-departments',
  standalone: true,
  imports: [MatButtonModule, DepartmentsTableComponent],
  templateUrl: './departments.component.html',
  styleUrl: './departments.component.scss',
})
export class DepartmentsComponent {
  constructor(
    private dialog: MatDialog,
    private departmentNotificationService: DepartmentNotificationService
  ) {}
  openCreateDepartmentModal(): void {
    this.dialog
      .open(DepartmentCreateUpdateModalComponent, {
        width: '400px',
      })
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          this.departmentNotificationService.notifyDepartmentCreated();
        }
      });
  }
}
