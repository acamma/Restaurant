import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  private getMenurUrl = "http://localhost:8090/restaurantServer/table/menu";

  constructor(private http: HttpClient) { }

  getMenu(): Observable<Map<string, any>> {
    return this.http.get<Map<string, any>>(this.getMenurUrl).pipe(
      catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message || "Server Error!");
  }
}
