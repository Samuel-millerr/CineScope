import "./SearchBanner.css";
import Button from "../../atoms/Button/Button";
import { Link } from "react-router-dom";

export default function SearchBanner() {
    return (
        <section className="search-banner">
            <h3>Não perca a oportunidade de adicionar os filmes que você mais gosta!</h3>
            <p>Clique no botão abaixo para participar de nossa historia.</p>
            <Link to={"/add-movie"}><Button variant={"blue-gradient"} text_button={"Adicione seu filme"}/></Link>
        </section>
    )
}