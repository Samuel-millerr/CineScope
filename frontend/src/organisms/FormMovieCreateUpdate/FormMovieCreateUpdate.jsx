import { useState } from "react";
import "./FormMovieCreateUpdate.css";
import Chip from "../../atoms/Chip/Chip.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import TextAreaGroup from "../../molecules/TextAreaGroup/TextAreaGroup.jsx";
import Button from "../../atoms/Button/Button.jsx";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup.jsx";

const mockData = {
    genero: ["Ação", "Aventura", "Drama", "Comédia", "Suspense", "Terror", "Romance", "Sci-Fi", "Fantasia", "Mistério", "Crime", "Animação", "Histórico", "Guerra", "Musical", "Esporte", "Thriller", "Documentário"],
    diretor: ["Christopher Nolan", "Steven Spielberg", "Martin Scorsese", "James Cameron", "Quentin Tarantino", "Ridley Scott", "Peter Jackson", "Denis Villeneuve", "David Fincher", "Guillermo del Toro", "Alfonso Cuarón", "Bong Joon-ho", "Guy Ritchie", "Tim Burton", "Patty Jenkins", "Greta Gerwig", "Francis Ford Coppola", "George Lucas", "Clint Eastwood", "Zack Snyder", "Lana Wachowski", "Anthony Russo", "Damien Chazelle", "Todd Phillips", "Frank Darabont", "Jon Favreau", "Chris Columbus"],
    elenco: ["Leonardo DiCaprio", "Morgan Freeman", "Scarlett Johansson", "Tom Hanks", "Natalie Portman", "Christian Bale", "Robert Downey Jr.", "Emma Stone", "Brad Pitt", "Anne Hathaway", "Keanu Reeves", "Jennifer Lawrence", "Johnny Depp", "Matt Damon", "Amy Adams", "Denzel Washington", "Chris Evans", "Gal Gadot", "Henry Cavill", "Mark Ruffalo", "Matthew McConaughey", "Heath Ledger", "Michael Caine", "Laurence Fishburne", "Carrie-Anne Moss", "Chris Hemsworth", "Kate Winslet", "Al Pacino", "Marlon Brando", "John Travolta", "Samuel L. Jackson", "Uma Thurman", "Ryan Gosling", "Song Kang-ho", "Joaquin Phoenix", "Robert De Niro", "Timothée Chalamet", "Zendaya", "Tim Robbins", "Mark Hamill", "Harrison Ford", "Carrie Fisher", "Chris Pine", "Edward Norton", "Daniel Radcliffe", "Emma Watson", "Rupert Grint", "Joseph Gordon-Levitt", "Jessica Chastain"]
};

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

const initialSelects = {
    genero: "",
    diretor: "",
    elenco: "",
};

const selectFields = [
    {
        name: "genero",
        label: "Gênero",
        placeholder: "Selecione um gênero...",
        options: mockData.genero
    },
    {
        name: "diretor",
        label: "Diretor(a)",
        placeholder: "Selecione um(a) diretor(a)...",
        options: mockData.diretor
    },
    {
        name: "elenco",
        label: "Elenco",
        placeholder: "Selecione um(a) ator/atriz...",
        options: mockData.elenco
    }
];

export default function FormMovieCreateUpdate({formData, setFormData}) {
    /* Componente de formulário para criação e edição de filmes, busca os dados necessarios do banco de dados e os insere nos selects e tem uma 
    uma lógica de passagem de props para ser possível a visualização do poster do filme */
    const [currentSelects, setCurrentSelects] = useState(initialSelects);

    const handleFormChange = (e) => {
        const { name, value } = e.target;

        if (Array.isArray(initialState[name])) {
            if (!value) return;

            setFormData((prevData) => {
                const currentList = prevData[name];
                if (currentList.includes(value)) {
                    return prevData;
                }
                return {
                    ...prevData,
                    [name]: [...currentList, value],
                };
            });

            setCurrentSelects(prev => ({ ...prev, [name]: "" }));
        } else {
            setFormData((prevData) => ({
                ...prevData,
                [name]: value,
            }));
        }
    };

    const handleRemoveItem = (field, itemToRemove) => {
        setFormData((prevData) => ({
            ...prevData,
            [field]: prevData[field].filter((item) => item !== itemToRemove),
        }));
    };

    const handleClear = () => {
        setFormData(initialState);
        setCurrentSelects(initialSelects);
    };

    return (
        <form className="form-movie">
            <InputGroup
                label={"Título do Filme"}
                placeholder={"Ex: Duna - Parte dois"}
                htmlFor={"titulo"}
                value={formData.titulo}
                onChange={handleFormChange}
                variant={"black"}
            />

            <div className="form-row">
                <InputGroup
                    label={"Ano de Lançamento"}
                    placeholder={"Ex: 2024"}
                    htmlFor={"ano"}
                    type={"number"}
                    value={formData.ano}
                    onChange={handleFormChange}
                    variant={"black"}
                />
                <InputGroup
                    label={"Duração"}
                    placeholder={"Ex: 2:32:54"}
                    htmlFor={"duracao"}
                    value={formData.duracao}
                    onChange={handleFormChange}
                    variant={"black"}
                />
            </div>

            {selectFields.map((field) => (
                <div className="form-row" key={field.name}>
                    <div>
                        <SelectGroup
                            label={field.label}
                            htmlFor={field.name}
                            onChange={handleFormChange}
                            placeholder={field.placeholder}
                            options={field.options}
                            value={currentSelects[field.name]}
                        />
                    </div>
                    <div className="chip-container">
                        {formData[field.name].map((item) => (
                            <Chip
                                key={item}
                                chip_text={item}
                                variant={"crud"}
                                removable={true}
                                onRemove={() => handleRemoveItem(field.name, item)}
                            />
                        ))}
                    </div>
                </div>
            ))}

            <TextAreaGroup
                label={"Sinopse"}
                placeholder={"Descreva a trama do filme..."}
                htmlFor={"sinopse"}
                value={formData.sinopse}
                onChange={handleFormChange}
                variant={"black"}
            />

            <InputGroup
                label={"URL do Poster"}
                placeholder={"Ex: https://exemplo.com/poster.jpg"}
                htmlFor={"posterUrl"}
                value={formData.posterUrl}
                onChange={handleFormChange}
                variant={"black"}
            />

            <div className="form-buttons">
                <Button variant={"transparent"} text_button={"Voltar"} type="button" />
                <Button variant={"transparent"} text_button={"Limpar"} onClick={handleClear} type="button" />
                <Button variant={"purple"} text_button={"Enviar Requisição"} type="submit" />
            </div>
        </form>
    );
}