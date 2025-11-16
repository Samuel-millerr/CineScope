import StarFull from "../../assets/icons/stars/star-full-icon.svg";
import StarHalf from "../../assets/icons/stars/star-half-icon.svg";
import StarEmpty from "../../assets/icons/stars/star-empty-icon.svg";

export default function Star({variant}) {
    // Componenete criado com o unico objetivo de permitir a mudança de preenchimento das estrelas
    let src;

    if (variant === "full")  {
        src = StarFull;
    } else if (variant === "half") {
        src = StarHalf;
    } else {
        src = StarEmpty;
    }

    return <img src={src} alt={`star-${variant}`}/>
}