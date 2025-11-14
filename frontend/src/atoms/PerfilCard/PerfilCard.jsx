import "./PerfilCard.css";

export default function PerfilCard({name_statistic, number_statistic = 0}) {
    return (
        <article className="perfil-card">
            <h2>{number_statistic}</h2>
            <p>{name_statistic}</p>
        </article>
    )
}