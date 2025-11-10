import "./NavBarLinks.css";
import Logo from "../../atoms/Logo/Logo.jsx";

export default function NavBar() {
    return (
        <nav className="nav-bar">
            <ul>
                <li><Logo/></li>
                <li>Home</li>
                <li>Minha Coleção</li>
                <li>Pesquisa</li>
            </ul>
        </nav>
    )
}