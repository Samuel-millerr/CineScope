import "./RatingFilter.css";
import RatingFilterLine from "../RatingFilterLine/RatingFilterLine.jsx";

export default function RatingFilter({ title, selectedRatings, onChange }) {
    // Componenete utilizado para demonstrar ao usuário as possíbilidades de filtro por avaliação
    const allRatings = [1, 2, 3, 4, 5];

    const handleChange = (rating) => {
        const newSelection = selectedRatings.includes(rating)
            ? selectedRatings.filter((item) => item !== rating)
            : [...selectedRatings, rating];

        onChange(newSelection);
    };

    return (
        <div className="rating-filter-container">
            <h3>{title}</h3>
            <ul className="rating-filter-list">
                {allRatings.map((rating) => (
                    <li key={rating} className="rating-filter-item">
                        <input
                            type="checkbox"
                            id={`rating-${rating}`}
                            className="rating-filter-checkbox"
                            checked={selectedRatings.includes(rating)}
                            onChange={() => handleChange(rating)}
                        />
                        <label htmlFor={`rating-${rating}`}>
                            <RatingFilterLine
                                rating={rating}
                                isSelected={selectedRatings.includes(rating)}
                            />
                        </label>
                    </li>
                ))}
            </ul>
        </div>
    );
};

