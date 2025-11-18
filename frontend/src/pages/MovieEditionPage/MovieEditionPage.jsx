import "./MovieEditionPage.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb";
import Footer from "../../organisms/Footer/Footer";
import FormMovieCreateUpdate, { initialState } from "../../organisms/FormMovieCreateUpdate/FormMovieCreateUpdate";
import PosterPreview from "../../molecules/PosterPreview/PosterPreview";

export default function MovieEditionPage() {
    const { movieId } = useParams();
    const [formData, setFormData] = useState(initialState);
    const [loading, setLoading] = useState(true);
    const [selectsLoaded, setSelectsLoaded] = useState(false);

    // Carrega opções dos selects
    const fetchSelectOptions = async () => {
        const [gRes, aRes, dRes] = await Promise.all([
            fetch("http://localhost:8000/api/genres"),
            fetch("http://localhost:8000/api/actors"),
            fetch("http://localhost:8000/api/directors"),
        ]);
        const [genres, actors, directors] = await Promise.all([gRes.json(), aRes.json(), dRes.json()]);
        return { genres, actors, directors };
    };

    useEffect(() => {
        let mounted = true;

        async function fetchAll() {
            try {
                const { genres, actors, directors } = await fetchSelectOptions();

                // Se não existe ID, formulário pronto para criação
                if (!movieId) {
                    if (mounted) {
                        setFormData(initialState);
                        setLoading(false);
                        setSelectsLoaded(true);
                    }
                    return;
                }

                // Carrega filme para edição
                const res = await fetch(`http://localhost:8000/api/movies/${movieId}`);
                const data = await res.json();

                if (!res.ok) {
                    alert("Filme não encontrado!");
                    if (mounted) setLoading(false);
                    return;
                }

                // Converte nomes/strings em IDs
                const mapNamesToIds = (items, list, idKey, nameKey) => {
                    if (!items) return [];
                    if (Array.isArray(items) && items.length > 0 && typeof items[0] === "number") return items;
                    if (Array.isArray(items) && typeof items[0] === "object") return items.map(x => x[idKey]).filter(Boolean);
                    if (typeof items === "string") {
                        return items.split(",").map(s => s.trim()).map(name => {
                            const found = list.find(x => {
                                const label = x[nameKey] || x.genre || x.actor_name || x.director_name;
                                return String(label).toLowerCase() === name.toLowerCase();
                            });
                            return found ? found[idKey] : null;
                        }).filter(Boolean);
                    }
                    return [];
                };

                const selectedGenres = mapNamesToIds(data.genres, genres, "id_genre", "genre");
                const selectedActors = mapNamesToIds(data.actors, actors, "id_actor", "actor_name");
                const selectedDirectors = mapNamesToIds(data.directors, directors, "id_director", "director_name");

                if (mounted) {
                    setFormData({
                        titulo: data.movie_title || "",
                        ano: data.publication_year || "",
                        duracao: data.duration_time || "",
                        genero: selectedGenres,
                        diretor: selectedDirectors,
                        elenco: selectedActors,
                        sinopse: data.movie_synopsis || "",
                        posterUrl: data.movie_poster || "",
                    });
                    setLoading(false);
                    setSelectsLoaded(true);
                }

            } catch (err) {
                console.error(err);
                alert("Erro ao carregar dados do filme.");
                if (mounted) setLoading(false);
            }
        }

        fetchAll();
        return () => { mounted = false; };
    }, [movieId]);

    if (loading) return <h1>Carregando...</h1>;

    return (
        <>
            <main className="movie-edition-page-container">
                <div>
                    <h1>{movieId ? "Edite o Filme" : "Adicione um Filme"}</h1>

                    <Breadcrumb items={{
                        "Perfil": "/perfil-profile",
                        "Filmes da Comunidade": "/perfil-my-collection",
                        [movieId ? "Editar Filme" : "Adicionar Filme"]: "#"
                    }} />

                    {selectsLoaded && (
                        <FormMovieCreateUpdate
                            formData={formData}
                            setFormData={setFormData}
                            isEditing={Boolean(movieId)}
                            movieId={movieId}
                            selectsLoaded={selectsLoaded}
                        />
                    )}
                </div>

                <div>
                    <PosterPreview posterUrl={formData.posterUrl} />
                </div>
            </main>
            <Footer />
        </>
    );
}
