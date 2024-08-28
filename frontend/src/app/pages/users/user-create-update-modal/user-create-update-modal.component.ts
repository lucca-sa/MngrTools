import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { catchError, Observable, of, switchMap } from 'rxjs';
import { UserDto } from '../../../shared/models/user.model';
import { DepartmentsService } from '../../../shared/services/departments.service';
import { UsersService } from '../../../shared/services/users.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-user-create-update-modal',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './user-create-update-modal.component.html',
  styleUrls: ['./user-create-update-modal.component.scss'],
})
export class UserCreateUpdateModalComponent implements OnInit {
  userForm: FormGroup;
  departmentName$: Observable<string> = of('');
  departmentNotFound = false;

  constructor(
    private fb: FormBuilder,
    private usersService: UsersService,
    private departmentsService: DepartmentsService,
    public dialogRef: MatDialogRef<UserCreateUpdateModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { userId?: number }
  ) {
    this.userForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required, Validators.maxLength(70)]],
      email: [
        '',
        [Validators.required, Validators.email, Validators.maxLength(50)],
      ],
      phone: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(15),
        ],
      ],
      address: ['', [Validators.required, Validators.maxLength(100)]],
      departmentId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.data && this.data.userId) {
      this.usersService.getUserById(this.data.userId).subscribe((user) => {
        this.userForm.patchValue({
          id: user.id,
          name: user.name,
          email: user.email,
          phone: user.phone,
          address: user.address,
          departmentId: user.department.id,
        });
        this.loadDepartmentName(user.department.id);
      });
    }
  }

  loadDepartmentName(departmentId: number): void {
    this.departmentNotFound = false;
    this.departmentName$ = this.departmentsService
      .getDepartmentById(departmentId)
      .pipe(
        switchMap((department) => {
          if (department) {
            return of(department.name);
          } else {
            this.departmentNotFound = true;
            return of('');
          }
        }),
        catchError(() => {
          this.departmentNotFound = true;
          return of('');
        })
      );
  }

  onDepartmentIdChange(departmentId: number): void {
    this.loadDepartmentName(departmentId);
  }

  save(): void {
    if (this.userForm.valid) {
      const userDto: UserDto = this.userForm.value;
      if (this.data?.userId) {
        this.usersService
          .updateUser(this.data.userId, userDto)
          .subscribe(() => {
            this.dialogRef.close(true);
          });
      } else {
        this.usersService.createUser(userDto).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}
