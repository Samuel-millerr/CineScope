import { useState, useEffect } from "react";
import "./FormMovieCreateUpdate.css";
import Chip from "../../atoms/Chip/Chip.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import TextAreaGroup from "../../molecules/TextAreaGroup/TextAreaGroup.jsx";
import Button from "../../atoms/Button/Button.jsx";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup.jsx";

export const initialState = {
    titulo: "",
    ano: "",
    duracao: "",
    genero: [],
    diretor: [],
    elenco: [],
    sinopse: "",
    posterUrl: "",
};

// decodificador simples de JWT payload (não verifica assinatura)
function parseJwt(token) {
    try {
        const payload = token.split('.')[1];
        const decoded = atob(payload.replace(/-/g, '+').replace(/_/g, '/'));
        return JSON.parse(decodeURIComponent(escape(decoded)));
    } catch (e) {
        return null;
    }
}

export default function FormMovieCreateUpdate({
    formData,
    setFormData,
    isEditing = false,
    movieId = null,
    selectsLoaded = false
}) {
    const [genres, setGenres] = useState([]);
    const [actors, setActors] = useState([]);
    const [directors, setDirectors] = useState([]);

    const [currentSelects, setCurrentSelects] = useState({
        genero: "",
        diretor: "",
        elenco: "",
    });

    const [isAdmin, setIsAdmin] = useState(false);

    // carrega options se não vierem de pai
    useEffect(() => {
        async function loadOptions() {
            try {
                const [gRes, aRes, dRes] = await Promise.all([
                    fetch("http://localhost:8000/api/genres"),
                    fetch("http://localhost:8000/api/actors"),
                    fetch("http://localhost:8000/api/directors"),
                ]);
                const [gData, aData, dData] = await Promise.all([gRes.json(), aRes.json(), dRes.json()]);
                setGenres(gData);
                setActors(aData);
                setDirectors(dData);
            } catch (err) {
                console.error("Erro ao carregar selects:", err);
            }
        }

        // se parent já carregou selects (selectsLoaded true) talvez não seja necessário, 
        // mas carregar localmente é seguro e idempotente.
        loadOptions();


    
        const token = localStorage.getItem("token");
        if (token) {
            const payload = parseJwt(token);
            if (payload && payload.role && payload.role.toLowerCase() === "administrador") {
                setIsAdmin(true);
            }
        }
    }, []);

    // trata mudanças nos selects e inputs
    const handleFormChange = (e) => {
        const { name, value } = e.target;

        // Se for select de N:N (array no formData)
        if (Array.isArray(formData[name])) {
            if (!value) return;

            // valor pode vir como string (id) -> converte para Number
            const numericValue = Number(value);

            setFormData(prev => ({
                ...prev,
                [name]: prev[name].includes(numericValue)
                    ? prev[name]
                    : [...prev[name], numericValue]
            }));

            // zera o select controlado
            setCurrentSelects(prev => ({ ...prev, [name]: "" }));
            return;
        }

        // Campos simples
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleRemoveItem = (field, value) => {
        setFormData(prev => ({
            ...prev,
            [field]: prev[field].filter(item => item !== value)
        }));
    };

    const handleClear = () => {
        setFormData(initialState);
        setCurrentSelects({ genero: "", diretor: "", elenco: "" });
    };

    // SUBMIT (POST or PUT)
    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem("token");
        if (!token) return alert("Você precisa estar logado!");

        const payload = {
            movie_title: formData.titulo,
            publication_year: Number(formData.ano),
            duration_time: formData.duracao,
            movie_synopsis: formData.sinopse,
            movie_poster: formData.posterUrl,
            genres: formData.genero,
            actors: formData.elenco,
            directors: formData.diretor,
        };

        const method = isEditing ? "PUT" : "POST";
        const url = isEditing
            ? `http://localhost:8000/api/movies/${movieId}`
            : "http://localhost:8000/api/movies";

        try {
            const res = await fetch(url, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify(payload),
            });

            const data = await res.json();

            if (res.ok) {
                alert(data.message || "Operação realizada com sucesso!");
                if (!isEditing) handleClear();
            } else {
                alert(data.error || "Erro ao enviar dados.");
            }
        } catch (err) {
            console.error(err);
            alert("Erro de conexão.");
        }
    };

    // Render
    return (
        <form className="form-movie" onSubmit={handleSubmit}>

            <InputGroup
                label="Título do Filme"
                placeholder="Ex: Duna - Parte dois"
                htmlFor="titulo"
                value={formData.titulo}
                onChange={handleFormChange}
                variant="black"
            />

            <div className="form-row">
                <InputGroup
                    label="Ano de Lançamento"
                    htmlFor="ano"
                    type="number"
                    value={formData.ano}
                    onChange={handleFormChange}
                    variant="black"
                    placeholder={"Ex: 1990"}
                />
                <InputGroup
                    label="Duração"
                    htmlFor="duracao"
                    value={formData.duracao}
                    onChange={handleFormChange}
                    variant="black"
                    placeholder={"02:12:45"}
                />
            </div>

            {/* GÊNERO */}
            <div className="form-row">
                <SelectGroup
                    label="Gênero"
                    htmlFor="genero"
                    name="genero"
                    onChange={handleFormChange}
                    options={genres.map(g => ({ value: g.id_genre, label: g.genre }))}
                    value={currentSelects.genero}
                />
                <div className="chip-container">
                    {formData.genero.map(id => {
                        const g = genres.find(x => x.id_genre === id);
                        return (
                            <Chip
                                key={id}
                                chip_text={g?.genre || id}
                                removable
                                onRemove={() => handleRemoveItem("genero", id)}
                                variant={"crud"}
                            />
                        );
                    })}
                </div>
            </div>

            {/* DIRETOR */}
            <div className="form-row">
                <SelectGroup
                    label="Diretor"
                    htmlFor="diretor"
                    name="diretor"
                    onChange={handleFormChange}
                    options={directors.map(d => ({ value: d.id_director, label: d.director_name }))}
                    value={currentSelects.diretor}
                />
                <div className="chip-container">
                    {formData.diretor.map(id => {
                        const d = directors.find(x => x.id_director === id);
                        return (
                            <Chip
                                key={id}
                                chip_text={d?.director_name || id}
                                removable
                                onRemove={() => handleRemoveItem("diretor", id)}
                                variant={"crud"}
                            />
                        );
                    })}
                </div>
            </div>

            {/* ELENCO */}
            <div className="form-row">
                <SelectGroup
                    label="Elenco"
                    htmlFor="elenco"
                    name="elenco"
                    onChange={handleFormChange}
                    options={actors.map(a => ({ value: a.id_actor, label: a.actor_name }))}
                    value={currentSelects.elenco}
                />

                <div className="chip-container">
                    {formData.elenco.map(id => {
                        const a = actors.find(x => x.id_actor === id);
                        return (
                            <Chip
                                key={id}
                                chip_text={a?.actor_name || id}
                                removable
                                onRemove={() => handleRemoveItem("elenco", id)}
                                variant={"crud"}
                            />
                        );
                    })}
                </div>
            </div>

            {/* SINOPSE */}
            <TextAreaGroup
                label="Sinopse"
                htmlFor="sinopse"
                value={formData.sinopse}
                onChange={handleFormChange}
                variant="black"
                placeholder={"Descreva a trama do filme..."}
            />

            {/* POSTER */}
            <InputGroup
                label="URL do Poster"
                htmlFor="posterUrl"
                value={formData.posterUrl}
                onChange={handleFormChange}
                variant="black"
                placeholder={"Ex: http://img_movie.png"}
            />

            {/* BUTTONS */}
            <div className="form-buttons">
                <Button variant="transparent" text_button="Limpar" onClick={handleClear} type="button" />
                <Button variant="purple" text_button={isEditing ? "Salvar Alterações" : "Criar Filme"} type="submit" />
            </div>
        </form>
    );
}
