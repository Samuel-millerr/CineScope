import "./NavBarUserGroup.css";
import UserIcon from "../../assets/icons/avatar-icon.svg";
import { Link } from "react-router-dom";

export default function NavBarUserGroup({
  displayName,
  linkTo = "/", // Adicionado um fallback
  userIcon = UserIcon,
}) {
  return (
    <div className="nav-bar-user-group">
      <Link to={linkTo} className="nav-user-avatar-wrapper">
        <span className="nav-user-name">{displayName}</span>
        <img
          className="nav-user-avatar"
          src={userIcon}
          alt="Ícone de Avatar"
        />
      </Link>
    </div>
  );
}