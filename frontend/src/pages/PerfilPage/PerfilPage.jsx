import "./PerfilPage.css";
import { Outlet } from "react-router-dom";
import PerfilMenu from "../../molecules/PerfilMenu/PerfilMenu.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";

export default function PerfilPage() {
    return (
        <>
            <main className="perfil-page-container">
                <PerfilMenu />
                <Outlet />
            </main>
            <Footer />
        </>
    )
}