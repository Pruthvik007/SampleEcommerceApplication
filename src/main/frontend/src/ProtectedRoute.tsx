import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import EcommerceService from "./services/EcommerceService";
interface ProtectedRouteProps {
  role: string;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ role }) => {
  const userRole = EcommerceService.getUserRole();
  // return userRole === role ? <Outlet /> : <Navigate to="/notfound" />;
  return <Outlet />;
};

export default ProtectedRoute;
