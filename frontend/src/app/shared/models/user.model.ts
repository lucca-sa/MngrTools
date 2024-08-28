import { Department } from './department.model';

export interface User {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  department: Department;
}

export interface UserDto {
  name: string;
  email: string;
  phone: string;
  address: string;
  departmentId: number;
}
