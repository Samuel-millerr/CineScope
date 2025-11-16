import "./SelectGroup.css"
import "../../molecules/InputGroup/InputGroup.css";

export default function SelectGroup({ label, htmlFor, onChange, placeholder, options = [] }) {
    // Componente usado como input para parte de criação e edição de filmes
    return (
        <>
            <label htmlFor={htmlFor} className="select-group-label">{label}</label>
            <select
                id={htmlFor}
                name={htmlFor}
                onChange={onChange}
                className="select-group-input"
            >
                <option value="" className="select-group-placeholder">{placeholder}</option>
                {options.map((item) => (
                    <option key={item} value={item}>
                        {item}
                    </option>
                ))}
            </select>
        </>
    );
}