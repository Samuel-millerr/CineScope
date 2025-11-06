import "./InputGroup.css";
import Input from "../../atoms/Input/Input.jsx";

export default function GroupInput({variant, label, type = "text", htmlFor, placeholder}) {
    return (
        <>
            <label htmlFor={htmlFor} className="input-label">{label}</label>
            <Input variant={variant} type={type} htmlFor={htmlFor} placeholder={placeholder}/>
        </>
    )
}