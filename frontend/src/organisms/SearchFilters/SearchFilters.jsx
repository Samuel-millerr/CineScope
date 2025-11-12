import "./SearchFilters.css";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import GenreFilter from "../../molecules/GenreFilter/GenreFilter.jsx";
import YearRangeFilter from "../../molecules/YearRangeFIlter/YearRangeFilter.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import RatingFilter from "../../molecules/RatingFilter/RatingFilter.jsx";
import { useState } from "react";

export default function SearchFilters() {
    const [selectedGenres, setSelectedGenres] = useState([]);
    const [yearRange, setYearRange] = useState({ min: 1990, max: 2025 });
    const [selectedRatings, setSelectedRatings] = useState([]);

    const handleYearRangeChange = (range) => {
        setYearRange(range);
        console.log("Faixa de anos atualizada:", range);
    };

    const handleRatingChange = (newRatings) => {
        setSelectedRatings(newRatings);
        console.log("Avaliações selecionadas:", newRatings);
    };

    return (
        <aside className="search-filters-container">
            <h2>Filtros</h2>
            <LineDivider variant={"transparent"} />
            <GenreFilter
                title={"Gênero"}
                options={["Ação", "Aventura", "Cuziho"]}
                selectedOptions={selectedGenres}
                onChange={setSelectedGenres}
            />
            <YearRangeFilter
                title={"Ano de Lançamento"}
                min={1950}
                max={2025}
                onChange={handleYearRangeChange}
            />
            <form className="search-form-container">
                <InputGroup variant={"white"} label={"Diretor(a)"} htmlFor={"director"} placeholder={"Nome do diretor/diretora"} />
                <InputGroup variant={"white"} label={"Ator/Atriz"} htmlFor={"actor"} placeholder={"Nome do ator/atriz"} />
            </form>
            <RatingFilter
                title="Avaliação"
                selectedRatings={selectedRatings}
                onChange={handleRatingChange}
            />
        </aside>
    )
}