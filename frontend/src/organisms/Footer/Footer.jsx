import "./Footer.css";
import FooterLinks from "../../molecules/FooterLinks/FooterLinks.jsx";
import MediaLinks from "../../molecules/MediaLinks/MediaLinks.jsx";

export default function Footer() {
    return (
        <footer className="footer">
            <FooterLinks />
            <MediaLinks />
            <p> © 2025 CineScope. Todos os direitos reservados. </p>
        </footer>
    )
}