import "./PerfilPage.css";
import { Outlet } from "react-router-dom";
import PerfilMenu from "../../molecules/PerfilMenu/PerfilMenu.jsx";
import NavBar from "../../organisms/NavBar/NavBar.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";

export default function PerfilPage() {
    return (
        <>
            <NavBar />
            <main className="perfil-page-container">
                <PerfilMenu />
                <Outlet />
            </main>
            <Footer />
        </>
    )
}