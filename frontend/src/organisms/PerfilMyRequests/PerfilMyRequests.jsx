import "./PerfilMyRequests.css";
import { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import RequestCard from "../../molecules/RequestCard/RequestCard.jsx";

export default function PerfilMyRequests() {
    // Componente que permite a visualização das requisições de edição e adicão feitas pelo usuário
    const [requests, setRequests] = useState([]);
    useEffect(() => {
        const loadUser = () => {
            try {
                url = "http://"
            }
            setRequests(fetchedRequests);
        };
        fetchRequests();
    }, []);

    return (
        <section className="perfil-section-conteiner perfil-requests-movie-container">
            <Title variant={"perfil"} title={"Adicione ou edite filmes já cadastrados"} />

            <div className="perfil-my-requests-line-conteiner">
                {requests.map((movie, index) => (
                    <RequestCard
                        key={index}
                        button_variant={"grey"}
                        text_button={"Editar"}
                        request={movie}
                    />
                ))}
            </div>
        </section>
    );
}
