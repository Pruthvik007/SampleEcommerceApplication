import Spinner from "./Spinner";

const BackDrop = () => {
  return (
    <div className="w-screen h-screen bg-transparent flex justify-center items-center">
      <Spinner />
    </div>
  );
};

export default BackDrop;
