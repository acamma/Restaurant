import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router/src/config';
import { LoginComponent } from 'src/app/login/login.component';
import { OrdiniComponent } from 'src/app/ordini/ordini.component';
import { RouterModule } from '@angular/router';

const routes: Routes = [
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "order", component: OrdiniComponent },
  { path: "**", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
