import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb";
import FormMovieCreateUpdate from "../../organisms/FormMovieCreateUpdate/FormMovieCreateUpdate";
import "./MovieAddPage.css";

export default function MovieAddPage() {
    return (
        <main>
            <h1>Cadastre o Filme</h1>
            <Breadcrumb items={{
                "Perfil": "/perfil",
                "Minha Coleção": "/perfil-my-collection",
                "Cadastrar Filmes": "#"
            }}/>
            <FormMovieCreateUpdate/>
        </main>
    )
}