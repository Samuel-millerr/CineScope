import "./PerfilMyRequests.css";
import { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import RequestCard from "../../molecules/RequestCard/RequestCard.jsx";

export default function PerfilMyRequests() {
    // Componente que permite a visualização das requisições de edição e adicão feitas pelo usuário
    const [requests, setRequests] = useState([]);
    useEffect(() => {
        const fetchRequests = () => {
            const fetchedRequests = Array.from({ length: 10 }).map(() => (
                {
                    movie_name: "Duna",
                    movie_image: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg",
                    request_date: "2025",
                    request_type: "Edição",
                    request_status: "Aprovado",
                }
            ));
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
