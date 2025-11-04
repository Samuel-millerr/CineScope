import FavoriteActive from "../../assets/icons/favorite/favorite-active-icon.svg"
import FavoriteInactive from "../../assets/icons/favorite/favorite-inactive-icon.svg"

export default function Favorite({type}) {
    let src;

    if (type === "inactive") {
        src = FavoriteInactive;
    } else if (type === "active") {
        src = FavoriteActive;
    }

    return <img src={src} alt={`favorite-${type}`}/>
}