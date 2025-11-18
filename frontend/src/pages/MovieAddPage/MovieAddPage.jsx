import "./MovieAddPage.css";
import { useState } from "react";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb";
import Footer from "../../organisms/Footer/Footer";
import FormMovieCreateUpdate, {initialState} from "../../organisms/FormMovieCreateUpdate/FormMovieCreateUpdate";
import PosterPreview from "../../molecules/PosterPreview/PosterPreview";

export default function MovieAddPage() {
    const [formData, setFormData] = useState(initialState);

    return (
        <>
            <main className="movie-add-page-container">
                <div>
                    <h1>Cadastre o Filme</h1>
                    <Breadcrumb items={{
                        "Perfil": "/perfil-profile",
                        "Filmes da Comunidade": "/perfil-my-collection",
                        "Cadastrar Filme": "#"
                    }} />
                    <FormMovieCreateUpdate
                        formData={formData}
                        setFormData={setFormData}
                    />
                </div>
                <PosterPreview posterUrl={formData.posterUrl} />
            </main>
            <Footer />
        </>
    )
}