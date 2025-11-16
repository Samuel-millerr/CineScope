import "./GenreFilter.css";

export default function GenreFilter({title, options, selectedOptions, onChange}) {
    // Agrupamento de filtros de gênero da parte de pesquisa
    
    const handleChange = (option) => {
        // Função utilizada para passar a seleção feita e passa para um lista das opções selecionadas
        // Verifica se a opção ja está selectionada, se estiver, a retira da lista, se nao a adiciona
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