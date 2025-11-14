import { useState } from "react";
import "./FormMovieCreateUpdate.css";
import Chip from "../../atoms/Chip/Chip.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import TextAreaGroup from "../../molecules/TextAreaGroup/TextAreaGroup.jsx";
import Button from "../../atoms/Button/Button.jsx";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup.jsx";

const mockData = {
    genero: ["Ficção Científica", "Aventura", "Ação", "Drama", "Comédia", "Terror", "Suspense", "Fantasia"],
    diretor: ["Denis Villeneuve", "Christopher Nolan", "Greta Gerwig", "Martin Scorsese", "Quentin Tarantino", "Bong Joon-ho"],
    elenco: ["Timothée Chalamet", "Zendaya", "Florence Pugh", "Austin Butler", "Anya Taylor-Joy", "Cillian Murphy", "Robert Downey Jr."],
    produtora: ["Warner Bros.", "Legendary Pictures", "A24", "Universal Pictures", "Sony Pictures", "Paramount Pictures", "Netflix"]
};

const initialState = {
    titulo: "",
    ano: "",
    duracao: "",
    genero: [],
    diretor: [],
    elenco: [],
    sinopse: "",
    posterUrl: "",
    produtora: []
};

const initialSelects = {
    genero: "",
    diretor: "",
    elenco: "",
    produtora: ""
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
    },
    {
        name: "produtora",
        label: "Produtora",
        placeholder: "Selecione uma produtora...",
        options: mockData.produtora
    }
];

export default function FormMovieCreateUpdate() {
    const [formData, setFormData] = useState(initialState);
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