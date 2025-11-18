import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

/*
ProtectedRoute:
- Permite acesso apenas se existir um token de autenticação.
- Caso contrário, redireciona para /login.

AdminRoute:
- Exige token e verifica se o usuário tem papel "administrador".
- Se não for admin, redireciona para a home (/).
Ambos utilizam o contexto de autenticação via useAuth().
*/

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
