import { useState } from "react";
import { APP_NAME, BASE_URL } from "../constants/Config";
import { Link } from "react-router-dom";
import ThemeToggle from "./common/ThemeToggle";
import CrossIcon from "../assets/svg-icons/CrossIcon";
import MenuIcon from "../assets/svg-icons/MenuIcon";

const navItems: { label: string; to: string }[] = [
  { label: "View Profile", to: "/profile" },
  { label: "Logout", to: "/logout" },
];
const Navbar = () => {
  const [isVisible, setIsVisible] = useState(false);
  const toggleNav = () => {
    setIsVisible(!isVisible);
  };

  return (
    <div className="bg-neutral flex justify-between items-center h-20 px-4 text-white">
      <Link id="desktop-logo" to={BASE_URL}>
        <h1 className="w-full text-xl font-bold text-success">{APP_NAME}</h1>
      </Link>
      <ul id="desktop-nav-items" className="hidden md:flex md:items-center">
        {navItems.map((item) => (
          <li
            key={item.label}
            className="p-4 hover:bg-[#00df9a] rounded-xl m-2 cursor-pointer duration-300 hover:text-black"
          >
            <Link to={item.to}>{item.label}</Link>
          </li>
        ))}
        <li className="p-2 rounded-xl m-2 cursor-pointer hover:bg-[#00df9a] flex items-center">
          <ThemeToggle className="w-10 h-10" />
        </li>
      </ul>
      <div
        id="mobile-nav-toggle"
        onClick={toggleNav}
        className="block md:hidden"
      >
        {isVisible ? <CrossIcon /> : <MenuIcon />}
      </div>
      <ul
        id="mobile-nav-items"
        className={
          isVisible
            ? "fixed md:hidden left-0 top-0 w-[60%] h-full border-r border-r-gray-900 bg-[#000300] ease-in-out duration-500"
            : "ease-in-out w-[60%] duration-500 fixed top-0 bottom-0 left-[-100%]"
        }
      >
        <div className="flex items-center justify-around p-3 gap-x-3">
          <Link id="mobile-logo" to={BASE_URL}>
            <h1 className="w-full font-bold text-[#00df9a] text-xs">
              {APP_NAME}
            </h1>
          </Link>
          <ThemeToggle className="w-8 h-8" />
        </div>
        {navItems.map((item) => (
          <li
            onClick={toggleNav}
            key={item.to}
            className="p-4 border-b rounded-xl hover:bg-[#00df9a] duration-300 hover:text-black cursor-pointer border-gray-600"
          >
            <Link to={item.to}>{item.label}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Navbar;
