import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginWaiterUrl = "http://localhost:8090/restaurantServer/employee/login";
  private logoutWaiterUrl = "http://localhost:8090/restaurantServer/employee/logout/";

  constructor(private http: HttpClient) { }

  loginWaiter(userData): Observable<any> {
    return this.http.post<any>(this.loginWaiterUrl, userData).pipe(
      catchError(this.errorHandler));
  }

  waiterLogged(): boolean {
    return !!localStorage.getItem("waiterId");
  }

  logoutWaiter(): Observable<number> {
    let id = localStorage.getItem("waiterId");
    return this.http.get<number>(this.logoutWaiterUrl + id).pipe(
      catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message || "Server Error!");
  }
}
