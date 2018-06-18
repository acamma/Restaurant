import { OrderDetail } from './order-detail';
export class Order {
  orderId: number;
  orderDate: Date;
  tableID: number;
  details: OrderDetail[];
  orderAmmount: number;
  invoice: string;
}
