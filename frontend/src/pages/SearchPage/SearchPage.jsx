import "./SearchPage.css";
import NavBar from "../../organisms/NavBar/NavBar.jsx";
import SearchBanner from "../../organisms/SearchBanner/SearchBanner.jsx";
import SearchFilters from "../../organisms/SearchFilters/SearchFilters.jsx";
import SearchMoviesLine from "../../organisms/SearchMoviesLine/SearchMoviesLine.jsx";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb.jsx";
import Title from "../../atoms/Title/Title.jsx";

export default function SearchPage() {
    const moviesMock = [
        {
            movie_poster: "https://uauposters.com.br/media/catalog/product/5/0/508320201013-uau-posters-filmes-a-origem-inception.jpg",
            movie_name: "Inception",
            movie_review_number: "3.9",
            favorite_icon_variant: "inactive",
        },
        {
            movie_poster: "https://uauposters.com.br/media/catalog/product/4/1/411320150509-uau-posters-filmes-interestelar-interestellar.jpg",
            movie_name: "Interstellar",
            movie_review_number: "4.0",
            favorite_icon_variant: "inactive",
        },
        {
            movie_poster: "https://m.media-amazon.com/images/I/5151N2hUPiL._AC_UF894,1000_QL80_.jpg",
            movie_name: "The Dark Knight",
            movie_review_number: "4.5",
            favorite_icon_variant: "inactive",
        },
    ];

    return (
        <>
            <header>
                <NavBar />
            </header>
            <main className="search-page-container">
                <SearchBanner />
                <section className="search-page-query-conteiner">
                    <div>
                        <Breadcrumb items={{
                            "Home": "/",
                            "Pesquisa": "/search"
                        }} />
                        <SearchFilters />
                    </div>
                    <div>
                        <Title variant={"search"} title={"Resultados"} />
                        <SearchMoviesLine movies={moviesMock} />
                        <SearchMoviesLine movies={moviesMock} />
                        <SearchMoviesLine movies={moviesMock.slice(0, 2)} />
                    </div>
                </section>
            </main>
        </>
    )
}