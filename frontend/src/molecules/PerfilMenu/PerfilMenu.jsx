import "./PerfilMenu.css";
import { Link } from "react-router-dom";
import LineDivider from "../../atoms/LineDivider/LineDivider";

export default function PerfilMenu(){
    return (
        <aside className="perfil-menu">
            <Link to={"/perfil"} className="perfil-menu-title">Meu Perfil</Link>
            <LineDivider variant={"gradient"}/>
            <Link to={"/perfil/my-collection"} className="perfil-menu-title">Minha Coleção</Link>
            <LineDivider variant={"gradient"}/>
            <Link to={"my-reviews"} className="perfil-menu-title">Minhas Avaliações</Link>
            <LineDivider variant={"gradient"}/>
            <Link to={"add-edit-movie"} className="perfil-menu-title">Adicione ou edite filmes</Link>
            <LineDivider variant={"gradient"}/>
            <p className="perfil-menu-title">Minhas Requisições</p>
        </aside>
    )
}