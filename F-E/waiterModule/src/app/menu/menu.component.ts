import { MenuService } from './../services/menu.service';
import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order';
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';
import { MenuItem } from '../models/menu-item';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  orderForm: FormGroup;
  items: any[] = [];
  menu: MenuItem[];
  order: Order;
  quantity: number[][];

  constructor(private menuService: MenuService, private fb: FormBuilder) { }

  ngOnInit() {
    this.order = new Order();
    this.order.details = [];
    this.getMenu();
  }

  getMenu() {
    this.menuService.getMenu().subscribe(
      res => {
        console.log(res);
        this.menu = res;
        this.quantity = Array(this.menu.length);
        this.menu.forEach((menuItem, i) => {
          this.quantity[i] = Array(menuItem.products.length);
          this.quantity[i].fill(0);
        });
        console.log(this.quantity);
      },
      error => alert(error)
    );
  }
}
