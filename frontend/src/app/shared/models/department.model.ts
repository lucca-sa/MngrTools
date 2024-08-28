export interface Department {
  id: number;
  name: string;
}

export interface DepartmentItem {
  id: number;
  name: string;
  hasUsers: boolean;
}

export interface DepartmentDto {
  name: string;
}
