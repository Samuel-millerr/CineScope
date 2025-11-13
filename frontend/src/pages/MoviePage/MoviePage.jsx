import "./MoviePage.css";
import NavBar from "../../organisms/NavBar/NavBar";
import Footer from "../../organisms/Footer/Footer";
import MovieDisplay from "../../organisms/MovieDisplay/MovieDisplay";
import MovieReviews from "../../organisms/MovieReviews/MovieReviews";

export default function MoviePage() {
    const movie_mock = {
        "movie_poster": "https://i.pinimg.com/474x/a9/72/38/a9723895209812ac0295848c667d524d.jpg",
        "movie_name": "A Origem",
        "movie_year": 2010,
        "movie_genre": "Action",
        "movie_duration": "2h 28min",
        "movie_summary": "Um ladrão que invade os sonhos das pessoas para roubar segredos corporativos precisa realizar a tarefa inversa: implantar uma ideia na mente de um alvo.",
        "movie_director": "Christopher Nolan",
        "movie_producer": "CuPreticios"
    }

    return (
        <>
            <NavBar />
            <main>
                <MovieDisplay movie={movie_mock}/>
                <section>
                    <MovieReviews/>
                </section>
            </main>
            <Footer/>
        </>
    )
}