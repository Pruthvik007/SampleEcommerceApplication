import { Suspense } from "react";
import { RouteObject, createBrowserRouter } from "react-router-dom";
import App from "./App.tsx";
import BackDrop from "./components/common/BackDrop.tsx";
import { BASE_URL } from "./constants/Config.ts";
import ProtectedRoute from "./ProtectedRoute.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import RegisterCustomerPage from "./pages/RegisterCustomerPage.tsx";
import PageNotFound from "./pages/PageNotFound.tsx";
import HomePage from "./pages/HomePage.tsx";
import AdminPage from "./pages/AdminPage.tsx";
import EmployeePage from "./pages/EmployeePage.tsx";

const publicRoutes: RouteObject[] = [
  {
    path: "/login",
    element: (
      <Suspense fallback={<BackDrop />}>
        <LoginPage />
      </Suspense>
    ),
  },
  {
    path: "/register",
    element: (
      <Suspense fallback={<BackDrop />}>
        <RegisterCustomerPage />
      </Suspense>
    ),
  },
  {
    path: "/pagenotfound",
    element: (
      <Suspense fallback={<BackDrop />}>
        <PageNotFound />
      </Suspense>
    ),
  },
  {
    path: "*",
    element: (
      <Suspense fallback={<BackDrop />}>
        <PageNotFound />
      </Suspense>
    ),
  },
];

const customerRoutes: RouteObject[] = [
  {
    path: BASE_URL,
    element: <ProtectedRoute role="CUSTOMER" />,
    children: [
      {
        path: BASE_URL,
        element: <App />,
        children: [
          {
            index: true,
            element: (
              <Suspense fallback={<BackDrop />}>
                <HomePage />
              </Suspense>
            ),
          },
        ],
      },
    ],
  },
];

const adminRoutes: RouteObject[] = [
  {
    path: "/admin",
    element: <ProtectedRoute role="ADMIN" />,
    children: [
      {
        path: BASE_URL,
        element: <App />,
        children: [
          {
            index: true,
            element: (
              <Suspense fallback={<BackDrop />}>
                <AdminPage />
              </Suspense>
            ),
          },
        ],
      },
    ],
  },
];

const employeeRoutes: RouteObject[] = [
  {
    path: "/employee",
    element: <ProtectedRoute role="EMPLOYEE" />,
    children: [
      {
        path: BASE_URL,
        element: <App />,
        children: [
          {
            index: true,
            element: (
              <Suspense fallback={<BackDrop />}>
                <EmployeePage />
              </Suspense>
            ),
          },
        ],
      },
    ],
  },
];

export const router = createBrowserRouter([
  ...publicRoutes,
  ...customerRoutes,
  ...adminRoutes,
  ...employeeRoutes,
]);
