import "./NavBar.css";
import NavBarLinks from "../../molecules/NavBarLinks/NavBarLinks.jsx";
import SearchBar from "../../atoms/SearchBar/SearchBar.jsx";
import NavBarUseGroup from "../../molecules/NavBarUserGroup/NavBarUserGroup.jsx";

export default function NavBar() {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("userType");

  let linkTo = "/login";
  let displayText = "Entrar";
  const isLoggedIn = !!token;

  if (isLoggedIn) {
    displayText = "Olá, Usuário"; 

    if (role?.toLowerCase() === "administrador") {
      linkTo = "/perfil-adm";
    } else if (role?.toLowerCase() === "comum") {
      linkTo = "/perfil";
    }
  }

  return (
    <nav className="navbar">
      <NavBarLinks />
      <SearchBar />
      <NavBarUseGroup displayName={displayText} linkTo={linkTo} />
    </nav>
  );
}