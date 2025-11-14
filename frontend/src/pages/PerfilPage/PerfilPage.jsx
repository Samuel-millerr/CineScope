import "./PerfilPage.css";
import { Outlet } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import PerfilMenu from "../../molecules/PerfilMenu/PerfilMenu.jsx";
import NavBar from "../../organisms/NavBar/NavBar.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";

export default function PerfilPage() {

    const [profileData, setProfileData] = useState(null);

    useEffect(() => {
        const fetchProfile = () => {
            setProfileData({
                nome: "Usuário 01",
                email: "usuario@email.com",
                membroDesde: "Janeiro 2023"
            });
        };

        fetchProfile();
    }, []);

    if (!profileData) {
        return <div>Carregando...</div>;
    }

    return (
        <>
            <NavBar />
            <main className="perfil-page-container">
                <PerfilMenu />
                {<Outlet />}
            </main>
            <Footer />
        </>
    )
}