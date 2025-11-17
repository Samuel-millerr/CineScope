import "./ActorCard.css";

export default function ActorCard({ actor }) {
    // Componenete para o card de ator presente na página indívidual de cada filme
    return (
        <article className="card-actor-conteiner">
            <figure className="actor-image-container">
                <img src={actor.actor_photo} className="actor-image" alt={`Foto do ator ${actor.actor_name}`}/>
            </figure>
            <h5 className="actor-name">{actor.actor_name}</h5>
        </article>
    )
}