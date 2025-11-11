import "./HeaderLinks.css";
import { Link } from "react-router-dom";
import Logo from "../../atoms/Logo/Logo.jsx";

export default function HeaderLinks() {
    return (
        <ul className="site-links">
            <li id="cinescope-logo"><Link to={"/"}><Logo/></Link></li>
            <li><Link to={"/"}>Home</Link></li>
            <li><Link to={"/my-collection"}>Minha Coleção</Link></li>
            <li><Link to={"/search"}>Pesquisa</Link></li>
        </ul>
    )
}