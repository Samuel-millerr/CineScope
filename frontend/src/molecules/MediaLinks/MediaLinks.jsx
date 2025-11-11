import "./MediaLinks.css";
import FacebookIcon from "../../assets/icons/media/facebook-icon.svg";
import TwitterIcon from "../../assets/icons/media/twitter-icon.svg";
import InstagramIcon from "../../assets/icons/media/instagram-icon.svg";

export default function MediaLinks() {
    return (
        <nav className="media-links">
            <figure>
                <a><img src={FacebookIcon} alt="Logo do facebook"></img></a>
            </figure>
            <figure>
                <a><img src={TwitterIcon} alt="Logo do twitter"></img></a>
            </figure>
            <figure>
                <a><img src={InstagramIcon} alt="Logo do instagram"></img></a>
            </figure>       
        </nav>
    )
}