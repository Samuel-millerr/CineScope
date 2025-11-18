import "./SelectGroup.css"
import "../../molecules/InputGroup/InputGroup.css";

export default function SelectGroup({
    label,
    htmlFor,
    options = [],
    value,
    onChange
}) {
    return (
        <div className="select-group">
            <label htmlFor={htmlFor} className="select-group-label">{label}</label>
            <select
                id={htmlFor}
                name={htmlFor}
                value={value}
                onChange={onChange}
                className="select-group-input"
            >
                <option value="" className="select-group-placeholder">Selecione...</option>

                {options.map(opt => (
                    <option key={opt.value} value={opt.value}>
                        {opt.label}
                    </option>
                ))}
            </select>
        </div>
    );
}
