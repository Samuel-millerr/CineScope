import { Link } from "react-router-dom";
import Button from "../../atoms/Button/Button";
import "./HomeFooterInfo.css";

export default function HomeFooterInfo({login = false}) {
    if (!login) {
        return (
            <section className="home-footer-info">
                <h2>Descubra seu próximo filme favorito!</h2>
                <p>
                    Crie uma conta gratuita no CineScope para salvar seus filmes favoritos, 
                    criar listas e receber recomendações personalizadas.
                </p>
                <Link to={"/login"}><Button type={"button"} variant={"blue-gradient"} text_button={"Comece Agora"}/></Link>
            </section>
    )}
    return;
}