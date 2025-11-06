import "./Button.css";

export default function Button({type="submit", variant, text_button}) {
    const classes = `button button-${variant}`;
    return <button className={classes} type={type}>{text_button}</button>
}