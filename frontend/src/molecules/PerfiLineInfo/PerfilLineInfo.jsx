import "./PerfilLineInfo.css";

export default function PerfilLineInfo({title, text}) {
    // Linha usada na parte de profile do perfil, onde e demonstrada as informações do usuário
    return (
        <div className="perfil-line-info">
            <h6>{title}</h6>
            <p>{text}</p>
        </div>
    )
}