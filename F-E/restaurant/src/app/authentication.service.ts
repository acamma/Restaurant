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
  private loginEmployeeUrl = "http://localhost:8090/restaurantServer/employee/login";
  private logoutEmployeerUrl = "http://localhost:8090/restaurantServer/employee/logout/";


  constructor(private http: HttpClient) { }

  loginTable(userData): Observable<any> {
    return this.http.post<any>(this.loginTableUrl, userData).pipe(
      catchError(this.errorHandler));
  }

  loginEmployee(userData): Observable<any> {
    return this.http.post<any>(this.loginEmployeeUrl, userData).pipe(
      catchError(this.errorHandler));
  }

  tableLoggedIn(): boolean {
    return !!localStorage.getItem("tableId");
  }

  employeeLoggedIn(): boolean {
    return !!localStorage.getItem("employeeId");
  }

  logoutTable(): Observable<number> {
    let id = localStorage.getItem("tableId");
    return this.http.get<number>(this.logoutTablerUrl + id).pipe(
      catchError(this.errorHandler));
  }

  logoutEmployee(): Observable<number> {
    let id = localStorage.getItem("employeeId");
    return this.http.get<number>(this.logoutEmployeerUrl + id).pipe(
      catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message || "Server Error!");
  }

}
