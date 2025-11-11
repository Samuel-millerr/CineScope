import "./HeaderLinks.css";
import Logo from "../../atoms/Logo/Logo.jsx";

export default function HeaderLinks() {
    return (
        <ul className="header-links">
            <li><Logo/></li>
            <li>Home</li>
            <li>Minha Coleção</li>
            <li>Pesquisa</li>
        </ul>
    )
}