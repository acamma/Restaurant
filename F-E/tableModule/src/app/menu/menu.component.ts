import { OrderDetail } from './../models/order-detail';
import { OrderService } from './../order.service';
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

  constructor(private ord: OrderService, private fb: FormBuilder) { }

  ngOnInit() {
    this.order = new Order();
    this.order.details = [];
    this.getMenu();
  }

  getMenu() {
    this.ord.getMenu().subscribe(
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

  getProductCategories(): FormArray {
    return this.orderForm.get('productCategories') as FormArray;
  }

  getProducts(): FormArray {
    return this.orderForm.get('products') as FormArray;
  }

  addToCart(productId: number, quantity: number) {
    let detail = new OrderDetail();
    detail.productId = productId;
    detail.quantity = quantity;
    this.order.details.push(detail);
    console.log(this.order);
  }

  updateCart(productId: number, quantity: number) {
    let detail = this.order.details.find(function (element) {
      return element.productId === productId;
    });
    if (detail && quantity !== 0) {
      detail.quantity = quantity;
    } else {
      this.removeFromCart(productId);
    }
    console.log(this.order);
  }

  removeFromCart(productId: number) {
    let details: OrderDetail[] = this.order.details.filter(function (element) {
      return element.productId !== productId;
    });
    this.order.details = [];
    details.forEach(function (element) {
      this.order.details.push(element);
    });
  }

  isChanged(quantity): number {
    return quantity;
  }
}
