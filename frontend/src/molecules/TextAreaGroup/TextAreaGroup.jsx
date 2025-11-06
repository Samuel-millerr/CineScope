import TextArea from "../../atoms/TextArea/TextArea.jsx";

export default function TextAreaGroup({variant, label, htmlFor, placeholder}) {
    return (
        <>
            <label htmlFor={htmlFor} className="input-label">{label}</label>
            <TextArea variant={variant} htmlFor={htmlFor} placeholder={placeholder} />
        </>
    )
}