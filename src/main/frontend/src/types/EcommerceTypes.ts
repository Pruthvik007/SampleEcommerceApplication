export interface Address {
  id: number;
  title: string;
  street: string;
  city: string;
  state: string;
  country: string;
  zip: string;
}

export interface Cart {
  id: number;
  items: Item[];
  totalValue: number;
}

export interface Category {
  id: number;
  name: string;
  description: string;
}

export interface Item {
  id: number;
  Product: Product;
  quantity: number;
}

export interface Order {
  id: number;
  orderItems: Item[];
  totalPrice: number;
  shippingAddress: Address;
}

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  image: string;
  categories: Category[];
}

export interface User {
  id: number;
  name: string;
  email: string;
  role: string;
  phoneNumber: string;
}
