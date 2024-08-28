import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DepartmentsService } from '../../../shared/services/departments.service';
import { ApiError } from '../../../shared/models/api-error.model';
import { DepartmentDto } from '../../../shared/models/department.model';

@Component({
  selector: 'app-department-create-update-modal',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './department-create-update-modal.component.html',
  styleUrls: ['./department-create-update-modal.component.scss'],
})
export class DepartmentCreateUpdateModalComponent implements OnInit {
  departmentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private departmentsService: DepartmentsService,
    public dialogRef: MatDialogRef<DepartmentCreateUpdateModalComponent>,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: { departmentId?: number }
  ) {
    this.departmentForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required, Validators.maxLength(70)]],
    });
  }

  ngOnInit(): void {
    if (this.data && this.data.departmentId) {
      this.departmentsService
        .getDepartmentById(this.data.departmentId)
        .subscribe((department) => {
          this.departmentForm.patchValue({
            id: department.id,
            name: department.name,
          });
        });
    }
  }

  save(): void {
    if (this.departmentForm.valid) {
      const departmentDto: DepartmentDto = this.departmentForm.value;
      if (this.data?.departmentId) {
        this.departmentsService
          .updateDepartment(this.data.departmentId, departmentDto)
          .subscribe({
            next: () => {
              this.dialogRef.close(true);
              this.snackBar.open('Department updated successfully', 'Close', {
                duration: 3000,
              });
            },
            error: (err: ApiError) => {
              this.snackBar.open('Failed to update department', 'Close', {
                duration: 3000,
              });
            },
          });
      } else {
        this.departmentsService.createDepartment(departmentDto).subscribe({
          next: () => {
            this.dialogRef.close(true);
            this.snackBar.open('Department created successfully', 'Close', {
              duration: 3000,
            });
          },
          error: (err: ApiError) => {
            this.snackBar.open('Failed to create department', 'Close', {
              duration: 3000,
            });
          },
        });
      }
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}
