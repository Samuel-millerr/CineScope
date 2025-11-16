import "./PerfilMenuAdm.css";
import LineDivider from "../../atoms/LineDivider/LineDivider";

export default function PerfilMenuAdm({ activeView, onNavigate }) {
    const getClassName = (viewName) => {
        return `perfil-menu-title ${activeView === viewName ? 'active' : ''}`;
    };

    return (
        <aside className="perfil-menu perfil-menu-adm">
            <a className={getClassName('filmes')} onClick={() => onNavigate('filmes')}> Filmes </a>
            <LineDivider variant={"gradient"} />
            <a className={getClassName('requisicoes')} onClick={() => onNavigate('requisicoes')}> Requisições </a>
            <LineDivider variant={"gradient"} /> <a className={getClassName('usuarios')} onClick={() => onNavigate('usuarios')}> Usuários </a>
        </aside>
    )
} 