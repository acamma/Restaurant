import { MenuService } from '../services/menu.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  orderForm: FormGroup;
  items: any[] = [];
  menu: Map<string, any>;

  constructor(private menuServ: MenuService) { }

  ngOnInit() {
    this.getMenu();
  }

  getMenu() {
    this.menuServ.getMenu().subscribe(
      res => {
        console.log(res);
        this.menu = res;
      },
      error => alert(error)
    );
  }

}
