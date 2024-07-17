import { ErrorType } from "../components/FormBuilder";
import { UserLoginDto, UserRegisterDto } from "../types/EcommerceDtoTypes";

class Validator {
  isValidEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  isValidUserLoginDetails = (
    user: Partial<UserLoginDto>
  ): { errors: ErrorType<UserLoginDto>; isValid: boolean } => {
    const { email, password } = user;
    const errors: ErrorType<UserLoginDto> = {} as ErrorType<UserLoginDto>;
    if (!email || email.trim() === "") {
      errors.email = "Email is Required";
    } else if (!this.isValidEmail(email)) {
      errors.email = "Invalid Email Format";
    }
    if (!password || password.trim() === "") {
      errors.password = "Password is Required";
    } else if (password.length < 6) {
      errors.password = "Password must be at least 6 Characters Long";
    }
    return { errors, isValid: Object.keys(errors).length === 0 };
  };

  isValidUserRegisterDetails = (
    user: Partial<UserRegisterDto>
  ): { errors: ErrorType<UserRegisterDto>; isValid: boolean } => {
    const { name } = user;
    let errors: ErrorType<UserRegisterDto> = {} as ErrorType<UserRegisterDto>;
    if (!name || name.trim() === "") {
      errors.name = "Name is Required";
    }
    errors = { ...errors, ...this.isValidUserLoginDetails(user).errors };
    if (user.password !== user.confirmPassword) {
      errors.submissionError = "Passwords do not Match. Please Check";
    }
    return { errors, isValid: Object.keys(errors).length === 0 };
  };
}
export default new Validator();
