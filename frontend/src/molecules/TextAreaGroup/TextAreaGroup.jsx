import "../InputGroup/InputGroup.css";
import TextArea from "../../atoms/TextArea/TextArea.jsx";

export default function TextAreaGroup({ variant, label, htmlFor, placeholder, value, onChange, }) {
    // Componente para textarea, pode mudar sua variação de acordo com a props 'variant'
    return (
        <div className="input-group">
            <label htmlFor={htmlFor} className="input-label">
                {label}
            </label>
            <TextArea
                variant={variant}
                htmlFor={htmlFor}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            />
        </div>
    );
}