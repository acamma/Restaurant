import { OrdersService } from './../orders.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menu: Map<string, any>;

  constructor(private ord: OrdersService) { }

  ngOnInit() {
    this.getMenu();
  }

  getMenu() {
    this.ord.getMenu().subscribe(
      res => {
        console.log(res);
        this.menu = res;
      },
      error => alert(error)
    );
  }
}
