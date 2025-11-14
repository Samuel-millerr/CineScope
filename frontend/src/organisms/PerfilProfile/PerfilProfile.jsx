import "./PerfilProfile.css";
import { useState } from "react";
import { useEffect } from "react";
import React from "react";
import Title from "../../atoms/Title/Title.jsx";
import PerfilLineInfo from "../../molecules/PerfiLineInfo/PerfilLineInfo.jsx";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import PerfilCard from "../../atoms/PerfilCard/PerfilCard.jsx";

export default function PerfilProfile() {
    const [profileData, setProfileData] = useState(null);

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
                <div className="profile-main-container">
                    Carregando informações do perfil...
                </div>
            </section>
        );
    }

    return (
        <section className="perfil-section-conteiner perfil-profile-container">
            <div className="profile-header-container">
                <div className="profile-banner"></div>
                <div className="profile-details">
                    <figure className="profile-picture">
                        <img />
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
                <article className="profile-statistics">
                    <Title variant={"perfil"} title={"Minhas Estatisticas"} />
                    <div className="profile-cards-conteiner">
                        <PerfilCard name_statistic={"Filmes Assistidos"} />
                        <PerfilCard name_statistic={"Filmes Avaliados"} />
                        <PerfilCard name_statistic={"Na sua Coleção"} />
                        <PerfilCard name_statistic={"Média de Notas"} />
                        <PerfilCard name_statistic={"Tempo Assistido"} />
                    </div>
                </article>
            </div>
        </section>
    )
}
