import "./NavBar.css";
import NavBarLinks from "../../molecules/NavBarLinks/NavBarLinks.jsx";
import SearchBar from "../../atoms/SearchBar/SearchBar.jsx";
import NavBarUseGroup from "../../molecules/NavBarUserGroup/NavBarUserGroup.jsx";

export default function NavBar() {
    return (
        <nav className="navbar">
            <NavBarLinks />
            <SearchBar />
            <NavBarUseGroup text={"Olá usuário"} />
        </nav>
    )
}