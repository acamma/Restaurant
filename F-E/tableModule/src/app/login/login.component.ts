import { LoginUserData } from '../models/login-user-data';
import { AuthenticationService } from './../authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginUserData = new LoginUserData();
  constructor(private auth: AuthenticationService, private router: Router) { }

  loginTable() {
    this.auth.loginTable(this.loginUserData).subscribe(
      res => {
        console.log(res);
        localStorage.setItem("tableId", String(res.tableId));
        this.router.navigate(["/menu"]);
      },
      error => alert(error)
    );
  }
}
