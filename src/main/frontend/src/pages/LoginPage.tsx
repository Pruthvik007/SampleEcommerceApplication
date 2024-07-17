import React from "react";
import FormBuilder from "../components/FormBuilder";
import validator from "../helpers/validator";
import { UserLoginDto } from "../types/EcommerceDtoTypes";

const LoginPage: React.FC = () => {
  const handleSubmit = (userLoginDto: Partial<UserLoginDto>) => {
    console.log("userLoginDto: ", userLoginDto);
  };

  return (
    <FormBuilder
      onSubmit={handleSubmit}
      submitButtonText="Login"
      initialData={{ email: "", password: "" }}
      metadata={[
        { name: "email", label: "Email", type: "email", required: true },
        {
          name: "password",
          label: "Password",
          type: "password",
          required: true,
        },
      ]}
      validator={validator.isValidUserLoginDetails}
    />
  );
};

export default LoginPage;
