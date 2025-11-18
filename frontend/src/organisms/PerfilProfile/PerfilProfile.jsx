import "./PerfilProfile.css";
import { useState, useEffect } from "react";
import Title from "../../atoms/Title/Title.jsx";
import PerfilLineInfo from "../../molecules/PerfiLineInfo/PerfilLineInfo.jsx";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import Button from "../../atoms/Button/Button.jsx";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../AuthContext";

export default function PerfilProfile() {
    const [profileData, setProfileData] = useState(null);
    const navigate = useNavigate();

    const { auth, logout } = useAuth();
    const userId = auth.user;

    useEffect(() => {
        if (!userId) return;
        
        const fetchProfile = async () => {
            try {
                const API_URL = `http://localhost:8000/api/users/${userId}`;

                const res = await fetch(API_URL);
                if (!res.ok) throw new Error("Erro ao carregar perfil");

                const data = await res.json();

                setProfileData({
                    "Nome:": data.user_name,
                    "Email:": data.email,
                    "Cadastro:": data.date_created
                });
            } catch (err) {
                console.error("Erro:", err);
            }
        };

        fetchProfile();
    }, [userId]);

    if (!profileData) {
        return (
            <section className="perfil-section-conteiner">
                <div className="error-states">Carregando informações do perfil...</div>
            </section>
        );
    }

    const handleExitAccount = () => {
        const decisao = confirm("Deseja realmente realizar o logout?");
        if (decisao) {
            logout();
            navigate("/", { replace: true });
        }
    };

    return (
        <section className="perfil-section-conteiner perfil-profile-container">
            <div className="profile-header-container">
                <div className="profile-banner"></div>

                <div className="profile-details">
                    <figure className="profile-picture">
                        <img alt="Avatar do usuário" />
                    </figure>

                    <h2 className="profile-username">{profileData.Nome}</h2>
                </div>
            </div>

            <div className="profile-main-container">
                <article className="profile-info">
                    <Title variant="perfil" title="Informações Pessoais" />

                    <div className="profile-info-text-conteiner">
                        {Object.entries(profileData).map(([key, value]) => (
                            <div key={key}>
                                <PerfilLineInfo title={key} text={value} />
                                <LineDivider variant="transparent" />
                            </div>
                        ))}
                    </div>
                </article>

                <div className="profile-footer">
                    <LineDivider variant="purple" />
                    <Button
                        text_button="Sair da Conta"
                        variant="red"
                        onClick={handleExitAccount}
                    />
                </div>
            </div>
        </section>
    );
}
