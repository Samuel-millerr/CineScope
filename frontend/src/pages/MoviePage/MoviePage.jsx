import "./MoviePage.css";
import NavBar from "../../organisms/NavBar/NavBar";
import Footer from "../../organisms/Footer/Footer";
import MovieDisplay from "../../organisms/MovieDisplay/MovieDisplay";
import ComunityReview from "../../molecules/ComunityReview/ComunityReview";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import RelatedMovieCard from "../../molecules/RelatedMovieCard/RelatedMovieCard.jsx";
import MyReview from "../../molecules/MyReview/MyReview.jsx";
import ActorCard from "../../molecules/ActorCard/ActorCard.jsx";

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
            <main className="movie-page-conteiner">
                <MovieDisplay movie={movie_mock} />
                <section className="movie-page-container-grid">
                    <article className="movie-reviews">
                        <h3>Críticas da Comunidade</h3>
                        <ComunityReview
                            user_name={"Samuel"}
                            comment_text={"Lorem Ipsum is simply dummy when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries."}
                            comment_date={"2 dias"}
                            rating={"3.9"}
                        />
                        <LineDivider variant={"transparent"}/>
                        <ComunityReview
                            user_name={"Samuel"}
                            comment_text={"Typesetting industry. When an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries."}
                            comment_date={"244 dias"}
                        />
                        <LineDivider variant={"transparent"}/>
                        <MyReview/>
                    </article>
                    <aside className="movie-page-related-line">
                        <h3>Filmes Relacionados</h3>
                        <RelatedMovieCard movie={movie_mock}/>
                        <RelatedMovieCard movie={movie_mock}/>
                        <RelatedMovieCard movie={movie_mock}/>
                    </aside>
                </section>
                <section>
                    <h2></h2>
                </section>
                <section className="movie-page-actor-conteiner">
                    <h4>Elenco Principal</h4>
                    <div>
                        <ActorCard actor_image={"https://br.web.img3.acsta.net/pictures/20/04/13/17/39/1048187.jpg"} actor_name="Cu Reymond"/>
                        <ActorCard actor_image={"https://br.web.img3.acsta.net/pictures/20/04/13/17/39/1048187.jpg"} actor_name="Cu Reymond"/>
                        <ActorCard actor_image={"https://br.web.img3.acsta.net/pictures/20/04/13/17/39/1048187.jpg"} actor_name="Cu Reymond"/>
                        <ActorCard actor_image={"https://br.web.img3.acsta.net/pictures/20/04/13/17/39/1048187.jpg"} actor_name="Cu Reymond"/>
                    </div>
                </section>
            </main>
            <Footer />
        </>
    )
}