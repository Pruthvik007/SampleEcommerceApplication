import React, { useState } from "react";
export type ErrorType<T> = {
  [K in keyof T]: string;
} & {
  submissionError?: string;
};
interface FieldMetadata {
  name: string;
  label: string;
  type: "text" | "email" | "password" | "number" | "date";
  required?: boolean;
  placeholder?: string;
}
//  extends Record<string, unknown>
interface FormBuilderProps<T> {
  initialData: T;
  metadata: FieldMetadata[];
  onSubmit: (data: T) => void;
  submitButtonText: string;
  validator: (data: T) => { errors: ErrorType<T>; isValid: boolean };
}

function FormBuilder<T>({
  initialData,
  metadata,
  onSubmit,
  submitButtonText,
  validator,
}: FormBuilderProps<T>) {
  const [formData, setFormData] = useState<T>(initialData);
  const [errors, setErrors] = useState<ErrorType<T>>({} as ErrorType<T>);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
    setErrors((prevErrors) => ({ ...prevErrors, [name]: "" }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const { errors: newErrors, isValid } = validator(formData);

    setErrors(newErrors);
    if (isValid) {
      onSubmit(formData);
    }
  };

  const reset = () => {
    setFormData(initialData);
    setErrors({} as ErrorType<T>);
  };

  return (
    <div className="min-h-screen bg-base-100 flex flex-col justify-center py-12 px-4 lg:px-8">
      <h1 className="sm:mx-auto sm:w-full sm:max-w-md mt-6 text-center text-3xl font-extrabold text-primary">
        {submitButtonText}
      </h1>
      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md bg-neutral py-8 px-4 shadow sm:rounded-lg sm:px-10">
        {errors.submissionError && (
          <h1 className="sm:mx-auto sm:w-full sm:max-w-md my-3 text-center text-xl text-error break-words">
            {errors.submissionError}
          </h1>
        )}
        <form className="space-y-6" onSubmit={handleSubmit}>
          {metadata.map((field) => (
            <div key={field.name}>
              <label
                htmlFor={field.name}
                className="block text-sm font-medium text-secondary"
              >
                {field.label}
              </label>
              <div className="mt-1">
                <input
                  id={field.name}
                  name={field.name}
                  type={field.type}
                  required={field.required}
                  placeholder={
                    field.placeholder || `Please Enter ${field.label}`
                  }
                  className="appearance-none block w-full px-3 py-2 border border-secondary rounded-md shadow-sm placeholder-neutral focus:outline-none focus:ring-primary focus:border-primary sm:text-sm"
                  value={formData[field.name as keyof T] as string}
                  onChange={handleInputChange}
                />
              </div>
              {errors[field.name as keyof T] && (
                <p className="mt-2 text-sm text-error">
                  {errors[field.name as keyof T]}
                </p>
              )}
            </div>
          ))}
          <div className="flex flex-col md:flex-row gap-3">
            <button
              onClick={reset}
              type="reset"
              className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-neutral bg-warning hover:bg-error focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
            >
              Reset
            </button>
            <button
              type="submit"
              className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-neutral bg-primary hover:bg-success focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
            >
              {submitButtonText}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default FormBuilder;
