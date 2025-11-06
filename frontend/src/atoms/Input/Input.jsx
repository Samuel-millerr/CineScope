import "./Input.css";

export default function Input({variant, type = "text", htmlFor, placeholder}) {
    const classes = `input input-${variant}`;
    return <input type={type} name={htmlFor} id={htmlFor} className={classes} placeholder={placeholder}/>
}