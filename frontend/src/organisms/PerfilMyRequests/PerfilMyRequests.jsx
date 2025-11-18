import "./PerfilMyRequests.css";
import { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import RequestCard from "../../molecules/RequestCard/RequestCard.jsx";
import { useAuth } from "../../AuthContext";

export default function PerfilMyRequests() {
    const [requests, setRequests] = useState([]);
    const { auth, logout } = useAuth();
    const userId = auth.user;
    useEffect(() => {
        const fetchRequests = async () => {
            try {
                const response = await fetch(`http://localhost:8000/api/requests_user/${userId}`);
                if (!response.ok) throw new Error("Erro ao buscar requisições");

                let data = await response.json();

                // Transformando request_body de string JSON para objeto
                data = data.map(req => {
                    let body = req.request_body;
                    if (typeof body === "string") {
                        try {
                            body = JSON.parse(body);
                        } catch (err) {
                            console.error("Erro ao parsear request_body:", err);
                            body = {};
                        }
                    }

                    return {
                        ...req,
                        movie_name: body.movie_title || "Sem título",
                        movie_image: body.movie_poster || "Sem imagem",
                        request_body: body
                    };
                });

                setRequests(data);
            } catch (error) {
                console.error("Erro ao carregar as requisições:", error);
            }
        };

        fetchRequests();
    }, []);

    return (
        <section className="perfil-section-conteiner perfil-requests-movie-container">
            <Title variant={"perfil"} title={"Adicione ou edite filmes já cadastrados"} />

            <div className="perfil-my-requests-line-conteiner">
                {requests.length > 0 ? (
                    requests.map((movie, index) => (
                        <RequestCard
                            key={index}
                            button_variant={"grey"}
                            text_button={"Editar"}
                            request={movie}
                        />
                    ))
                ) : (
                    <p className="error-states">Nenhuma requisição encontrada.</p>
                )}
            </div>
        </section>
    );
}
