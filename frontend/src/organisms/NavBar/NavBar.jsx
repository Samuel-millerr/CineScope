import "./NavBar.css";
import HeaderLinks from "../../molecules/HeaderLinks/HeaderLinks.jsx";
import SearchBar from "../../atoms/SearchBar/SearchBar.jsx";
import HeaderUserGroup from "../../molecules/HeaderUserGroup/HeaderUserGroup.jsx";


export default function NavBar() {
    return (
        <nav className="navbar">
            <HeaderLinks/>
            <SearchBar/>
            <HeaderUserGroup text={"Olá usuário"}/>
        </nav>
    )
}