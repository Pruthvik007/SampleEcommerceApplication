import FormBuilder from "../components/FormBuilder";
import validator from "../helpers/validator";
import { UserRegisterDto } from "../types/EcommerceDtoTypes";

const RegisterCustomerPage = () => {
  const handleSubmit = (userRegisterDto: Partial<UserRegisterDto>) => {
    console.log("userRegisterDto: ", userRegisterDto);
  };

  return (
    <FormBuilder
      onSubmit={handleSubmit}
      submitButtonText="Register"
      initialData={{ name: "", email: "", password: "", confirmPassword: "" }}
      metadata={[
        { name: "name", label: "Name", type: "text", required: true },
        { name: "email", label: "Email", type: "email", required: true },
        {
          name: "password",
          label: "Password",
          type: "password",
          required: true,
        },
        {
          name: "confirmPassword",
          label: "Confirm Password",
          type: "password",
          required: true,
          placeholder: "ReType Your Password",
        },
      ]}
      validator={validator.isValidUserRegisterDetails}
    />
  );
};

export default RegisterCustomerPage;
