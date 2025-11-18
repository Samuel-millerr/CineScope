import "./SearchFilters.css";
import { useState, useEffect } from "react";
import { fetchAllGenres } from "../../services/genreServices";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import GenreFilter from "../../molecules/GenreFilter/GenreFilter.jsx";
import YearRangeFilter from "../../molecules/YearRangeFIlter/YearRangeFilter.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import RatingFilter from "../../molecules/RatingFilter/RatingFilter.jsx";

export default function SearchFilters({ selectedGenres, setSelectedGenres, setYearRange, selectedRatings, setSelectedRatings, director, setDirector, actor, setActor }) {
    // Componente de busca pronto e completo com todos os filtros possíveis
    const [genres, setGenres] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadGenres = async () => {
            try {
                const data = await fetchAllGenres();
                setGenres(data);
            } catch (err) {
                setError(err.message)
            } finally {
                setIsLoading(false)
            };
        }

        loadGenres();
    }, []);

    const handleYearRangeChange = (range) => {
        setYearRange(range);
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
                options={genres}
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
                <InputGroup
                    variant={"white"}
                    label={"Diretor(a)"}
                    htmlFor={"director"}
                    placeholder={"Nome do diretor/diretora"}
                    value={director}
                    onChange={(e) => setDirector(e.target.value)}
                />
                <InputGroup
                    variant={"white"}
                    label={"Ator/Atriz"}
                    htmlFor={"actor"}
                    placeholder={"Nome do ator/atriz"}
                    value={actor}
                    onChange={(e) => setActor(e.target.value)}
                />
            </form>
            <RatingFilter
                title="Avaliação"
                selectedRatings={selectedRatings}
                onChange={handleRatingChange}
            />
        </aside>
    )
}