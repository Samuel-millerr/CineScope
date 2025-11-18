import "./GenreFilter.css";

export default function GenreFilter({ title, options, selectedOptions, onChange }) {
    // Agrupamento de filtros de gênero da parte de pesquisa
    const handleChange = (option) => {
        // Ao escolher a opção específica o sistema recebe isso e adiciona em uma lista
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
                    <li key={option.id_genre} className="genre-filter-item">
                        <input
                            type="checkbox"
                            id={`genre-${option.id_genre}`}
                            className="genre-filter-checkbox"
                            checked={selectedOptions.includes(option.genre)}
                            onChange={() => handleChange(option.genre)}
                        />
                        <label htmlFor={`genre-${option.id_genre}`}>{option.genre}</label>
                    </li>
                ))}
            </ul>
        </div>
    );
};