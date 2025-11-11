import "./HomeBanner.css";
import Button from "../../atoms/Button/Button.jsx"

export default function HomeBanner() {
    return (
        <section className="home-banner">
            <h1>Filme em Destaque</h1>
            <p>
                Acompanhe Michael Corleone em sua transformação 
                de herói de guerra a líder implacável da máfia. Violência, estratégia e drama em cada cena. Clique e mergulhe nesse clássico que marcou gerações!
            </p>
            <div className="home-banner-container">
                <a href="https://www.youtube.com/watch?v=SaHZHU-44XA"><Button variant={"blue-gradient"} text_button={"Assistir Trailer"}/></a>
                <Button variant={"transparent"} text_button={"Mais informações"}/>
            </div>
        </section>
    )
}