import "./MediaLinks.css";
import FacebookIcon from "../../assets/icons/media/facebook-icon.svg";
import TwitterIcon from "../../assets/icons/media/twitter-icon.svg";
import InstagramIcon from "../../assets/icons/media/instagram-icon.svg";

export default function MediaLinks() {
    // Componenete para aos links das redes sociais no footer
    return (
        <nav className="media-links">
            <figure>
                <a href="https://www.facebook.com/?locale=pt_BR" target="blank"><img src={FacebookIcon} alt="Logo do facebook"></img></a>
            </figure>
            <figure>
                <a href="https://x.com" target="blank"><img src={TwitterIcon} alt="Logo do twitter"></img></a>
            </figure>
            <figure>
                <a href="https://www.instagram.com" target="blank"><img src={InstagramIcon} alt="Logo do instagram"></img></a>
            </figure>       
        </nav>
    )
}