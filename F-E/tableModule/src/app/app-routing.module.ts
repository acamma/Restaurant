import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { TableGuard } from './table.guard';
import { OrderComponent } from './order/order.component';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  { path: "", redirectTo: "/menu", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "order", component: OrderComponent, canActivate: [TableGuard] },
  { path: "menu", component: MenuComponent, canActivate: [TableGuard] },
  { path: "**", component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
