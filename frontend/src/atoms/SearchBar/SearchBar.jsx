import "./SearchBar.css";
import SearchIcon from "../../assets/icons/search-icon.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom"; // --- 1. Importar useNavigate ---

export default function SearchBar() {
    const [query, setQuery] = useState("");
    
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        if (query.trim() === "") {
            return;
        }
        
        navigate(`/search?q=${query}`);
    }

    return (
        <form className="search-bar" onSubmit={handleSubmit}>
            <input 
                className="search-bar-input" type="search" name="search" placeholder="Buscar filme..." value={query} onChange={(e) => setQuery(e.target.value)}/>
            <button className="search-bar-button" type="submit">
                <img src={SearchIcon} alt="Icone de lupa" />
            </button>
        </form>
    )
}