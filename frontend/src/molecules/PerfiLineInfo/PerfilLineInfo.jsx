import "./PerfilLineInfo.css";

export default function PerfilLineInfo({title, text}) {
    return (
        <div className="PerfilLineInfo">
            <h6>{title}</h6>
            <p>{text}</p>
        </div>
    )
}