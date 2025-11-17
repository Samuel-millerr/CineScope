import "./MovieEditionPage.css";
import { useState } from "react";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb";
import Footer from "../../organisms/Footer/Footer";
import FormMovieCreateUpdate, { initialState } from "../../organisms/FormMovieCreateUpdate/FormMovieCreateUpdate";
import PosterPreview from "../../molecules/PosterPreview/PosterPreview";
import HistoryMenu from "../../molecules/HistoryMenu/HistoryMenu.jsx";

export default function MovieEditionPage() {
    const [formData, setFormData] = useState(initialState);

    return (
        <>  
            <main className="movie-edition-page-container">
                <div>
                    <h1>Edite o Filme</h1>
                    <Breadcrumb items={{
                        "Perfil": "/perfil-profile",
                        "Filmes da Comunidade": "/perfil-my-collection",
                        "Editar Filme": "#"
                    }} />
                    <FormMovieCreateUpdate
                        formData={formData}
                        setFormData={setFormData}
                    />
                </div>
                <div>
                    <HistoryMenu edition_histories={[{"date": "28/08/2020", "time": "2:00", "user": "samuel"}]}/>
                    <PosterPreview posterUrl={formData.posterUrl} />
                </div>
            </main>
            <Footer />
        </>
    )
}