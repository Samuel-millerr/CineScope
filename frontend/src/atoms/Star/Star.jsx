import StarFull from "../../assets/icons/stars/star-full-icon.svg"
import StarHalf from "../../assets/icons/stars/star-half-icon.svg"
import StarEmpty from "../../assets/icons/stars/star-empty-icon.svg"

export default function Star({type}) {
    let src;

    if (type === "full")  {
        src = StarFull;
    } else if (type === "half") {
        src = StarHalf;
    } else {
        src = StarEmpty
    }

    return <img src={src} alt={`star-${type}`}/>
}