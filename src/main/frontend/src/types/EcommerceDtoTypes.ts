export interface UserLoginDto {
  email: string;
  password: string;
}

export interface UserRegisterDto extends UserLoginDto {
  name: string;
  confirmPassword: string;
}

export interface EmployeeRegisterDto extends UserRegisterDto {
  userRole: "EMPLOYEE" | "ADMIN";
}

export interface AddressDto {
  title: string;
  street: string;
  city: string;
  state: string;
  country: string;
  zip: string;
}

export interface CartDto {
  cartItems: {
    [key: number]: number;
  };
}

export interface CategoryCreateDto {
  name: string;
  description: string;
}

export interface CategoryUpdateDto extends CategoryCreateDto {
  id: number;
}

export interface ProductCreateDto {
  name: string;
  description: string;
  price: number;
  image: string;
  categoryIds: number[];
}

export interface ProductUpdateDto extends ProductCreateDto {
  id: number;
}
