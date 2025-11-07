import "./PerfilMenu.css";
import LineDivider from "../../atoms/LineDivider/LineDivider";

export default function PerfilMenu(){
    return (
        <aside className="perfil-menu">
            <h3 className="perfil-menu-title">Meu Perfil</h3>
            <LineDivider variant={"gradient"}/>
            <h3 className="perfil-menu-title">Minha Coleção</h3>
            <LineDivider variant={"gradient"}/>
            <h3 className="perfil-menu-title">Minhas Avaliações</h3>
            <LineDivider variant={"gradient"}/>
            <h3 className="perfil-menu-title">Adicione ou edite filmes</h3>
            <LineDivider variant={"gradient"}/>
            <h2 className="perfil-menu-title">Minhas Requisições</h2>
        </aside>
    )
}