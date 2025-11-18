import { Link } from "react-router-dom";
import "./SmallButton.css";

export default function SmallButton({ type = "submit", variant, text_button }) {
    // Botão pequeno, utilizado em certas ocasiões, possue variação de acordo com a classe passada
    const classes = `small-button small-button-${variant}`;
    if (variant === "red") {
        return <button className={classes} type={type}>{text_button}</button>
    } else if (variant === "grey") {
        return <button className={classes} type={type}>{text_button}</button>
    }
}