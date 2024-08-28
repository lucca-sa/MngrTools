export interface ApiError {
  error: string;
  message: string | Record<string, string>;
  timestamp: string;
  status: number;
}
