export interface Response {
  status: "SUCCESS" | "ERROR" | "FAILURE";
  message: string;
  data: unknown;
}

export interface PageResponse extends Response {
  pageNo: number;
  pageSize: number;
  totalPages: number;
}
