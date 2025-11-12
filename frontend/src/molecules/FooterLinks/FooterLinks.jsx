import "../NavBarLinks/NavBarLinks.css";
import { Link } from "react-router-dom";

export default function FooterLinks() {
    return (
        <nav>
            <ul className="site-links">
                <li><Link to={"/about-us"}> Sobre Nós </Link></li>
                <li><Link to={"/about-us"}> Contato </Link></li>
            </ul>
        </nav>
    )
}