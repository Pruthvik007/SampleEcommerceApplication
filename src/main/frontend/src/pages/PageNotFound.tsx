import React from "react";
import { Link } from "react-router-dom";
import { PageNotFoundIllustration } from "../assets/assets";

const PageNotFound: React.FC = () => {
  return (
    <div className="min-h-screen bg-base-100 flex items-center justify-center px-4">
      <div className="max-w-lg w-full text-center">
        <h1 className="text-6xl font-bold text-primary mb-2">404</h1>
        <h2 className="text-3xl font-semibold text-secondary mb-4">
          Page Not Found
        </h2>
        <p className="text-warning mb-8">
          Oops! The page you're looking for doesn't exist or has been moved.
        </p>
        <img
          src={PageNotFoundIllustration}
          alt="404 Illustration"
          className="w-64 md:w-80 h-64 mx-auto mb-8"
        />
        <Link
          to="/"
          className="bg-accent text-white font-bold py-2 px-4 rounded hover:bg-accent/80 transition-colors duration-300  text-sm sm:text-base md:text-lg md:py-3 md:px-6"
        >
          Go Back Home
        </Link>
      </div>
    </div>
  );
};

export default PageNotFound;
