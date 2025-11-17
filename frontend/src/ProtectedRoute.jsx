import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children }) {
  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  return children;
}

export function AdminRoute({ children }) {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("userType");

  if (!token) return <Navigate to="/login" replace />;

  if (role !== "Administrador" && role !== "administrador") {
    return <Navigate to="/" replace />;
  }

  return children;
}
