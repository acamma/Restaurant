import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginTableUrl = "http://localhost:8090/restaurantServer/table/login";
  private logoutTablerUrl = "http://localhost:8090/restaurantServer/table/logout/";

  constructor(private http: HttpClient) { }

  loginTable(userData): Observable<any> {
    return this.http.post<any>(this.loginTableUrl, userData).pipe(
      catchError(this.errorHandler));
  }

  tableLoggedIn(): boolean {
    return !!localStorage.getItem("tableId");
  }

  logoutTable(): Observable<number> {
    let id = localStorage.getItem("tableId");
    return this.http.get<number>(this.logoutTablerUrl + id).pipe(
      catchError(this.errorHandler));
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message || "Server Error!");
  }
}
