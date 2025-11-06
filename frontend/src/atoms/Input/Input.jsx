import "./Input.css";

export default function Input({type = "text", name, variant, placeholder}) {
    const classes = `input input-${variant}`;
    return <input type={type} name={name} className={classes} placeholder={placeholder}/>
}