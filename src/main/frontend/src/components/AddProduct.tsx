import { ProductCreateDto } from "../types/EcommerceDtoTypes";
import FormBuilder, { ErrorType } from "./FormBuilder";

const AddProduct = () => {
  const product: ProductCreateDto = {
    name: "",
    description: "",
    price: 0,
    image: "",
    categoryIds: [],
  };
  return (
    <FormBuilder
      initialData={product}
      metadata={[
        { label: "Name", name: "name", type: "text", required: true },
        {
          label: "Description",
          name: "description",
          type: "text",
          required: true,
        },
        { label: "Price", name: "price", type: "number", required: true },
        { label: "Image", name: "image", type: "text", required: true },
        {
          label: "Categories",
          name: "categoryIds",
          type: "text",
          required: true,
        },
      ]}
      onSubmit={(product) => {
        console.log(product);
      }}
      submitButtonText="Add Product"
      validator={() => ({
        errors: {} as ErrorType<ProductCreateDto>,
        isValid: true,
      })}
    />
  );
};

export default AddProduct;
