import "./HeaderUserGroup.css";
import UserIcon from "../../assets/icons/avatar-icon.svg";

export default function HeaderUserGroup({text, user_icon={UserIcon}}) {
    return (
        <article className="header-user-group">
            <h4>{text}</h4>
            <figure>
                <img src={user_icon} alt="Ícone de Avatar"/>
            </figure>
        </article>
    )
}