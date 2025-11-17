import "./PerfilProfile.css";
import { useState } from "react";
import { useEffect } from "react";
import Title from "../../atoms/Title/Title.jsx";
import PerfilLineInfo from "../../molecules/PerfiLineInfo/PerfilLineInfo.jsx";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import Button from "../../atoms/Button/Button.jsx";
import { useNavigate } from "react-router-dom";

export default function PerfilProfile() {
    // Componente principal da tela de perfil
    const [profileData, setProfileData] = useState(null);
    const navigate = useNavigate()

    useEffect(() => {
        const fetchProfile = () => {
            setProfileData({
                "Nome:": "Usuário 01",
                "Email": "usuario@email.com",
                "Cadastro:": "Janeiro 2023"
            });
        };

        fetchProfile();
    }, []);

    if (!profileData) {
        return (
            <section className="perfil-section-conteiner">
                <div className="error-states">
                    Carregando informações do perfil...
                </div>
            </section>
        );
    }

    const handleExitAccount = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("userType");
        localStorage.removeItem("userName");
        
        const decisao = confirm("Deseja realmente realizar o logout?")
        if (decisao) {
            setTimeout(() => navigate("/"), 1200);
        }
    };

    return (
        <section className="perfil-section-conteiner perfil-profile-container">
            <div className="profile-header-container">
                <div className="profile-banner"></div>
                <div className="profile-details">
                    <figure className="profile-picture">
                        <img alt="Avatar do usuário"/>
                    </figure>
                    <h2 className="profile-username">Usuário 01</h2>
                </div>
            </div>
            <div className="profile-main-container">
                <article className="profile-info">
                    <Title variant={"perfil"} title={"Informações Pessoais"} />
                    <div className="profile-info-text-conteiner">
                        {
                            Object.entries(profileData).map(([key, value]) =>
                                <>
                                    <PerfilLineInfo
                                        key={key}
                                        title={key}
                                        text={value}
                                    />
                                    <LineDivider variant={"transparent"} />
                                </>
                            )}
                    </div>
                </article>
                <div className="profile-footer">
                    <LineDivider variant={"purple"} />
                    <Button text_button={"Sair da Conta"} variant={"red"} onClick={handleExitAccount}/>
                </div>
            </div>
        </section>
    )
}
