import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginUserData } from '../models/login-user-data';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginWaiterData = new LoginUserData();

  constructor(
    private auth: AuthenticationService,
    private router: Router
  ) { }

  loginWaiter() {
    this.auth.loginWaiter(this.loginWaiterData).subscribe(
      res => {
        localStorage.setItem("waiterId", String(res.employeeId));
        console.log(res);
        this.router.navigate(["/menu"]);
      },
      error => alert(error)
    );
  }
}
