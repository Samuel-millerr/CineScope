import "./PerfilMenuAdm.css";
import LineDivider from "../../atoms/LineDivider/LineDivider";

export default function PerfilMenuAdm({ onNavigate }) {
    return (
        <aside className="perfil-menu perfil-menu-adm">
            <a className="perfil-menu-title" onClick={() => onNavigate('filmes')}> Filmes </a>
            <LineDivider variant={"gradient"} />
            <a className="perfil-menu-title" onClick={() => onNavigate('requisicoes')}> Requisições </a>
            <LineDivider variant={"gradient"} /> 
            <a className="perfil-menu-title" onClick={() => onNavigate('usuarios')}> Usuários </a>
        </aside>
    )
} 