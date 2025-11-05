import "./PerfilCard.css"

export default function PerfilCard({number_statistic, name_statistic}) {
    return (
        <article className="perfil-card">
            <h2>{number_statistic}</h2>
            <p>{name_statistic}</p>
        </article>
    )
}