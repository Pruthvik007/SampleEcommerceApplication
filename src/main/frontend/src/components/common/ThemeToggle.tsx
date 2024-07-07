import { useEffect, useState } from "react";
import MoonIcon from "../../assets/svg-icons/MoonIcon";
import SunIcon from "../../assets/svg-icons/SunIcon";

const ThemeToggle = ({ className = "" }: { className?: string }) => {
  const [theme, setTheme] = useState(
    localStorage.getItem("color-theme") || "light"
  );

  useEffect(() => {
    if (typeof window !== "undefined") {
      if (
        theme === "dark" ||
        (!localStorage.getItem("color-theme") &&
          window.matchMedia("(prefers-color-scheme: dark)").matches)
      ) {
        document.documentElement.classList.add("dark");
      } else {
        document.documentElement.classList.remove("dark");
      }
    }
  }, [theme]);

  const toggleTheme = () => {
    if (theme === "light") {
      setTheme("dark");
      localStorage.setItem("color-theme", "dark");
    } else {
      setTheme("light");
      localStorage.setItem("color-theme", "light");
    }
  };

  return (
    <button
      onClick={toggleTheme}
      id="theme-toggle"
      type="button"
      className={className}
      aria-label="Toggle theme"
    >
      {theme === "light" ? <MoonIcon /> : <SunIcon />}
    </button>
  );
};

export default ThemeToggle;
