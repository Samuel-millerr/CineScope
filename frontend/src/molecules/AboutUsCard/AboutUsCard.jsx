import "./AboutUsCard.css";

export default function AboutUsCard({ value_icon, value_title, value_text }) {
    // Card de sobre nos, com o objetivo de armazenar os valores do projeto
    return (
        <article className="about-us-card">
            <figure>
                <img src={value_icon} alt={`Icone de ${value_title}`} />
            </figure>
            <h4>{value_title}</h4>
            <p>{value_text}</p>
        </article>
    )
}