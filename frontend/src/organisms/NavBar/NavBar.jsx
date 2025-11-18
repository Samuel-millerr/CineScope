import "./NavBar.css";
import NavBarLinks from "../../molecules/NavBarLinks/NavBarLinks.jsx";
import SearchBar from "../../atoms/SearchBar/SearchBar.jsx";
import NavBarUserGroup from "../../molecules/NavBarUserGroup/NavBarUserGroup.jsx";
import { useAuth } from "../../AuthContext.jsx";

export default function NavBar() {
  const { auth, logout } = useAuth(); 
  const isLoggedIn = !!auth?.token;

  let linkTo = "/login";
  let displayText = "Entrar";

  if (isLoggedIn) {
    displayText = auth.user ? `Olá, ${auth.user}` : "Olá, Usuário";

    if (auth.role?.toLowerCase() === "administrador") {
      linkTo = "/perfil-adm";
    } else if (auth.role?.toLowerCase() === "comum") {
      linkTo = "/perfil";
    }
  }

  return (
    <nav className="navbar">
      <NavBarLinks />
      <SearchBar />
      <NavBarUserGroup displayName={displayText} linkTo={linkTo} />
    </nav>
  );
}
