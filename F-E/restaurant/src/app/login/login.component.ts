import { LoginUserData } from '../models/login-user-data';
import { AuthenticationService } from './../authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUserData = new LoginUserData();
  constructor(private auth: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.switchview();
  }

  loginUser() {
    if (this.loginUserData.username.includes("tavolo"))
      this.loginTable();
    else
      this.loginEmployee();
  }

  loginTable() {
    this.auth.loginTable(this.loginUserData).subscribe(
      res => {
        console.log(res);
        if (res.tableId) {
          localStorage.setItem("tableId", String(res.tableId));
        } else if (res.employeeId) {
          localStorage.setItem("employeeId", String(res.employeeId));
        } this.switchview();
      },
      error => alert(error)
    );
  }

  loginEmployee() {
    this.auth.loginEmployee(this.loginUserData).subscribe(
      res => {
        console.log(res);
        if (res.tableId) {
          localStorage.setItem("tableId", String(res.tableId));
        } else if (res.employeeId) {
          localStorage.setItem("employeeId", String(res.employeeId));
        } this.switchview();
      },
      error => alert(error)
    );
  }

  switchview() {
    if (this.auth.employeeLoggedIn()) {
      this.router.navigate(["/orders"]);
    } else if (this.auth.tableLoggedIn()) {
      this.router.navigate(["/menu"]);
    }
  }
}
