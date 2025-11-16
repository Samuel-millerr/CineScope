import "./NavBarUserGroup.css";
import UserIcon from "../../assets/icons/avatar-icon.svg";
import { Link } from "react-router-dom";

export default function HeaderUserGroup({text, user_icon=UserIcon}) {
    // Agrupamento de foto de nome do usuário
    return (
        <article className="nav-bar-user-group">
            <p>{text}</p>
            <figure>
                <Link to={"/perfil"}><img src={user_icon} alt="Ícone de Avatar"/></Link>
            </figure>
        </article>
    )
}