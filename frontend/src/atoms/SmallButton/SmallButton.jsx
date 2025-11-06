import "./SmallButton.css";

export default function SmallButton({type="submit", variant, text_button}) {
    const classes = `small-button small-button-${variant}`;
    return <button className={classes} type={type}>{text_button}</button>
}