import "./InputGroup.css";
import Input from "../../atoms/Input/Input.jsx";

export default function InputGroup({ variant, label, type = "text", htmlFor, placeholder, value, onChange }) {
    // Componenete de grupo de inputs label+input
    return (
        <div className="input-group">
            <label htmlFor={htmlFor} className="input-label">
                {label}
            </label>
            <Input
                variant={variant}
                type={type}
                htmlFor={htmlFor}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            />
        </div>
    );
}