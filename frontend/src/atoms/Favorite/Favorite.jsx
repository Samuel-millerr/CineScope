import FavoriteActive from "../../assets/icons/favorite/favorite-active-icon.svg";
import FavoriteInactive from "../../assets/icons/favorite/favorite-inactive-icon.svg";

export default function Favorite({variant}) {
    let src;

    if (variant === "inactive") {
        src = FavoriteInactive;
    } else if (variant === "active") {
        src = FavoriteActive;
    }

    return <img src={src} alt={`favorite-${variant}`}/>
}