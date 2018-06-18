import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    public auth: AuthenticationService, 
    private router: Router
  ) { }

  logoutWaiter() {
    this.auth.logoutWaiter().subscribe(
      numrows => {
        if (numrows > 0) {
          localStorage.removeItem("waiterId");
          this.router.navigate(["/login"]);
        } else {
          alert("Error while loggin out");
        }
      },
      error => alert(error)
    );
  }

}
