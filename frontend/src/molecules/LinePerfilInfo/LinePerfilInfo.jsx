import "./LinePerfilInfo.css";

export default function LinePerfilInfo({title, text}) {
    return (
        <div className="LinePerfilInfo">
            <h6>{title}</h6>
            <p>{text}</p>
        </div>
    )
}