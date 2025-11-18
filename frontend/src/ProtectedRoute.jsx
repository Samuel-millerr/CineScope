import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

export default function ProtectedRoute({ children }) {
  const { auth } = useAuth();

  if (!auth.token) {
    return <Navigate to="/login" replace />;
  }

  return children;
}

export function AdminRoute({ children }) {
  const { auth } = useAuth();

  if (!auth.token) {
    return <Navigate to="/login" replace />;
  }

  if (auth.role?.toLowerCase() !== "administrador") {
    return <Navigate to="/" replace />;
  }

  return children;
}
