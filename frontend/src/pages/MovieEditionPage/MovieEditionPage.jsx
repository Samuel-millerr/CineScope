import "./MovieEditionPage.css";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb";
import Footer from "../../organisms/Footer/Footer";
import FormMovieCreateUpdate from "../../organisms/FormMovieCreateUpdate/FormMovieCreateUpdate";
import NavBar from "../../organisms/NavBar/NavBar";
import HistoryMenu from "../../molecules/HistoryMenu/HistoryMenu";

export default function MovieEditionPage() {
    return (
        <>
            <NavBar />
            <main className="movie-edition-page-container">
                <div>
                <h1>Edite o Filme</h1>
                <Breadcrumb items={{
                    "Perfil": "/perfil-profile",
                    "Filmes da Comunidade": "/perfil-my-collection",
                    "Editar Filme": "#"
                }} />
                <FormMovieCreateUpdate />
                </div>
                <HistoryMenu edition_histories={[{"date": "28/08/2020", "time": "2:00", "user": "samuel"}]}/>
            </main>
            <Footer />
        </>
    )
}