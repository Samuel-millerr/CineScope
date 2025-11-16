import "./SearchBar.css";
import SearchIcon from "../../assets/icons/search-icon.svg";

export default function SearchBar() {
    // Componenete básico de barra de busca
    return (
        <form className="search-bar">
            <input className="search-bar-input" type="search" name="search" placeholder="Buscar..." />
            <button className="search-bar-button" type="submmit"><img src={SearchIcon} alt="Icone de lupa" /></button>
        </form>
    )
}