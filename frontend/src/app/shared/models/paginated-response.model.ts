export interface PaginatedResponse<T> {
  page: number;
  totalItems: number;
  totalPage: number;
  data: T[];
}
