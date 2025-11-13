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

export default function FormMovieCreateUpdate() {
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

    const [formData, setFormData] = useState(initialState);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleAddItem = (e) => {
        const { name, value } = e.target;

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
        e.target.value = "";
    };

    const handleRemoveItem = (field, itemToRemove) => {
        setFormData((prevData) => ({
            ...prevData,
            [field]: prevData[field].filter((item) => item !== itemToRemove),
        }));
    };

    const handleClear = () => {
        setFormData(initialState);
    };

    return (
        <form className="form-movie">
            <InputGroup
                label={"Título do Filme"}
                placeholder={"Ex: Duna - Parte dois"}
                htmlFor={"titulo"}
                value={formData.titulo}
                onChange={handleChange}
                variant={"black"}
            />

            <div className="form-row">
                <InputGroup
                    label={"Ano de Lançamento"}
                    placeholder={"Ex: 2024"}
                    htmlFor={"ano"}
                    type={"number"}
                    value={formData.ano}
                    onChange={handleChange}
                    variant={"black"}
                />
                <InputGroup
                    label={"Duração"}
                    placeholder={"Ex: 2:32:54"}
                    htmlFor={"duracao"}
                    value={formData.duracao}
                    onChange={handleChange}
                    variant={"black"}
                />
            </div>

            <div className="form-row">
                <div>
                    <SelectGroup
                        label="Gênero"
                        htmlFor="genero"
                        onChange={handleAddItem}
                        placeholder="Selecione um gênero..."
                        options={mockData.genero}
                    />
                </div>
                <div className="chip-container">
                    {formData.genero.map((item) => (
                        <Chip
                            key={item}
                            chip_text={item}
                            variant={"crud"}
                            removable={true}
                            onRemove={() => handleRemoveItem("genero", item)}
                        />
                    ))}
                </div>
            </div>

            <div className="form-row">
                <div>
                    <SelectGroup
                        label="Diretor(a)"
                        htmlFor="diretor"
                        onChange={handleAddItem}
                        placeholder="Selecione um(a) diretor(a)..."
                        options={mockData.diretor}
                    />
                </div>
                <div className="chip-container">
                    {formData.diretor.map((item) => (
                        <Chip
                            key={item}
                            chip_text={item}
                            variant={"crud"}
                            removable={true}
                            onRemove={() => handleRemoveItem("diretor", item)}
                        />
                    ))}
                </div>
            </div>

            <div className="form-row">
                <div>
                    <SelectGroup
                        label="Elenco"
                        htmlFor="elenco"
                        onChange={handleAddItem}
                        placeholder="Selecione um(a) ator/atriz..."
                        options={mockData.elenco}
                    />
                </div>
                <div className="chip-container">
                    {formData.elenco.map((item) => (
                        <Chip
                            key={item}
                            chip_text={item}
                            variant={"crud"}
                            removable={true}
                            onRemove={() => handleRemoveItem("elenco", item)}
                        />
                    ))}
                </div>
            </div>

            <div className="form-row">
                <div>
                    <SelectGroup
                        label="Produtora"
                        htmlFor="produtora"
                        onChange={handleAddItem}
                        placeholder="Selecione uma produtora..."
                        options={mockData.produtora}
                    />
                </div>
                <div className="chip-container">
                    {formData.produtora.map((item) => (
                        <Chip
                            key={item}
                            chip_text={item}
                            variant={"crud"}
                            removable={true}
                            onRemove={() => handleRemoveItem("produtora", item)}
                        />
                    ))}
                </div>
            </div>

            <TextAreaGroup
                label={"Sinopse"}
                placeholder={"Descreva a trama do filme..."}
                htmlFor={"sinopse"}
                value={formData.sinopse}
                onChange={handleChange}
                variant={"black"}
            />

            <InputGroup
                label={"URL do Poster"}
                placeholder={"Ex: https://exemplo.com/poster.jpg"}
                htmlFor={"posterUrl"}
                value={formData.posterUrl}
                onChange={handleChange}
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