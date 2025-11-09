import "./GenreFilter.css";

export default function GenreFilter({title, options, selectedOptions, onChange}) {
    
    const handleChange = (option) => {
        const newSelection = selectedOptions.includes(option)
            ? selectedOptions.filter((item) => item !== option)
            : [...selectedOptions, option];

        onChange(newSelection);
    };

    return (
        <div className="genre-filter-container">
            <h3>{title}</h3>
            <ul className="genre-filter-list">
                {options.map((option) => (
                    <li key={option} className="genre-filter-item">
                        <input
                            type="checkbox"
                            id={`genre-${option}`}
                            className="genre-filter-checkbox"
                            checked={selectedOptions.includes(option)}
                            onChange={() => handleChange(option)}
                        />
                        <label htmlFor={`genre-${option}`}>{option}</label>
                    </li>
                ))}
            </ul>
        </div>
    );
};