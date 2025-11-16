import "./NavBarLinks.css";
import { Link } from "react-router-dom";
import Logo from "../../atoms/Logo/Logo.jsx";

export default function NavBarLinks() {
    // Links da navbar para permitir a navegação entre as telas
    return (
        <ul className="site-links">
            <li id="cinescope-logo"><Link to={"/"}><Logo/></Link></li>
            <li><Link to={"/"}>Home</Link></li>
            <li><Link to={"/perfil/my-collection"}>Minha Coleção</Link></li>
            <li><Link to={"/search"}>Pesquisa</Link></li>
        </ul>
    )
}