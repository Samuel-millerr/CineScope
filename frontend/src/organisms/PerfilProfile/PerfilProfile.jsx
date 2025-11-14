import "./PerfilProfile.css";
import Title from "../../atoms/Title/Title.jsx";
import PerfilLineInfo from "../../molecules/PerfiLineInfo/PerfilLineInfo.jsx";
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";
import PerfilCard from "../../atoms/PerfilCard/PerfilCard.jsx";

export default function PerfilProfile() {
    const { profile } = useOutletContext();

    if (!profile) {
        return <div>Nenhum perfil encontrado.</div>;
    }

    return (
        <section className="perfil-profile-conteiner">
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
                            Object.entries(profile).map(([key, value]) =>
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
