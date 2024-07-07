import { Outlet } from "react-router-dom";
import "./App.css";
import ContextProvider from "./context/ContextProvider";
import Navbar from "./components/NavBar";

function App() {
  return (
    <ContextProvider>
      <Navbar />
      <Outlet />
    </ContextProvider>
  );
}

export default App;
