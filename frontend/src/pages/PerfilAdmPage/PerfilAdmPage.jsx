import "./PerfilAdmPage.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

import PerfilMenuAdm from "../../molecules/PerfilMenuAdm/PerfilMenuAdm.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";
import AdmListTable from "../../organisms/AdmListTable/AdmListTable.jsx";

import EditIcon from "../../assets/icons/admin-icons/edit-icon.svg";
import EyeIcon from "../../assets/icons/admin-icons/eye-icon.svg";
import TrashIcon from "../../assets/icons/admin-icons/trash-icon.svg";
import UserRemoveIcon from "../../assets/icons/admin-icons/user-remove-icon.svg";
import ApprovedIcon from "../../assets/icons/admin-icons/approved-icon.svg";
import ReprovedIcon from "../../assets/icons/admin-icons/reproved-icon.svg";

import { fetchMoviesAdmList } from "../../services/movieService";
import {
    fetchPendingRequests,
    approveRequest,
    denyRequest
} from "../../services/requestService";

const ITEMS_PER_PAGE = 10;

export default function AdminDashboard() {
    const [activeView, setActiveView] = useState("filmes");
    const [searchTerm, setSearchTerm] = useState("");
    const [currentPage, setCurrentPage] = useState(1);

    const [apiMovieData, setApiMovieData] = useState([]);
    const [loadingMovies, setLoadingMovies] = useState(false);
    const [errorMovies, setErrorMovies] = useState(null);

    const [apiRequestData, setApiRequestData] = useState([]);
    const [loadingRequests, setLoadingRequests] = useState(false);
    const [errorRequests, setErrorRequests] = useState(null);

    useEffect(() => {
        if (activeView !== "filmes") return;

        setLoadingMovies(true);
        setErrorMovies(null);

        const loadMovies = async () => {
            try {
                const data = await fetchMoviesAdmList();

                const transformed = data.map(movie => ({
                    id: `#${String(movie.id_movie).padStart(2, "0")}`,
                    filme: movie.movie_title,
                    avaliacaoMedia: movie.avg_rating,
                    original_id: movie.id_movie
                }));

                setApiMovieData(transformed);
            } catch (err) {
                setErrorMovies(err.message);
            } finally {
                setLoadingMovies(false);
            }
        };

        loadMovies();
    }, [activeView]);

    const loadRequests = async () => {
        setLoadingRequests(true);
        setErrorRequests(null);

        try {
            const data = await fetchPendingRequests();

            const transformed = data.map(req => ({
                id: `#R${String(req.id_request).padStart(2, "0")}`,
                original_id: req.id_request,
                filme: req.request_body.movie_title,
                solicitante: req.user_name,
                dataRequisicao: req.request_date,
                tipoRequisicao: req.request_type,
                status: req.request_status
            }));

            setApiRequestData(transformed);

        } catch (err) {
            setErrorRequests(err.message);
        } finally {
            setLoadingRequests(false);
        }
    };

    useEffect(() => {
        if (activeView === "requisicoes") {
            loadRequests();
        }
    }, [activeView]);

    const handleApproveRequest = async (id) => {
        if (!window.confirm("Permitir publicação deste filme?")) return;

        try {
            await approveRequest(id);
            alert("Filme aprovado e publicado!");
            loadRequests();
        } catch (err) {
            alert(err.message);
        }
    };

    const handleDenyRequest = async (id) => {
        if (!window.confirm("Recusar esta solicitação?")) return;

        try {
            await denyRequest(id);
            alert("Solicitação recusada.");
            loadRequests();
        } catch (err) {
            alert(err.message);
        }
    };

    const movieColumns = [
        { label: "Filme ID", accessor: "id" },
        { label: "Filme", accessor: "filme" },
        { label: "Avaliação Média", accessor: "avaliacaoMedia", className: "rating-cell" },
        {
            label: "Ações",
            accessor: "acoes",
            className: "action-buttons",
            render: (row) => (
                <>
                    <Link to={`/movie/${row.original_id}`}>
                        <figure><img src={EyeIcon} alt="Ver" /></figure>
                    </Link>
                    <Link to={`/edit-movie/${row.original_id}`}>
                        <figure><img src={EditIcon} alt="Editar" /></figure>
                    </Link>
                    <a title="Deletar" onClick={() => alert("Função deletar ainda não implementada")}>
                        <figure><img src={TrashIcon} alt="Remover" /></figure>
                    </a>
                </>
            )
        }
    ];

    const requestsColumns = [
        { label: "Requisição ID", accessor: "id" },
        { label: "Filme Alvo", accessor: "filme" },
        { label: "Solicitante", accessor: "solicitante" },
        { label: "Data da Requisição", accessor: "dataRequisicao" },
        { label: "Tipo da Requisição", accessor: "tipoRequisicao" },
        { label: "Status", accessor: "status" },
        {
            label: "Ações",
            accessor: "acoes",
            className: "action-buttons",
            render: (row) => (
                <>
                    <a title="Aprovar" onClick={() => handleApproveRequest(row.original_id)}>
                        <figure><img src={ApprovedIcon} alt="Aprovar" /></figure>
                    </a>
                    <a title="Recusar" onClick={() => handleDenyRequest(row.original_id)}>
                        <figure><img src={ReprovedIcon} alt="Recusar" /></figure>
                    </a>
                </>
            )
        }
    ];

    const userColumns = [
        { label: "Usuário ID", accessor: "id" },
        { label: "Usuário", accessor: "usuario" },
        { label: "Nome Completo", accessor: "nomeCompleto" },
        { label: "Data de Cadastro", accessor: "dataCadastro" },
        { label: "E-mail", accessor: "email" },
        {
            label: "Ações",
            accessor: "acoes",
            render: (row) => (
                <a title="Deletar" onClick={() => alert(`Deletar usuário ${row.id}`)}>
                    <figure><img src={UserRemoveIcon} alt="Deletar usuário" /></figure>
                </a>
            )
        }
    ];

    let dataToFilter;
    let columnsToShow;
    let titleForTable;

    if (activeView === "filmes") {
        dataToFilter = apiMovieData;
        columnsToShow = movieColumns;
        titleForTable = "Filmes Cadastrados";
    }
    else if (activeView === "requisicoes") {
        dataToFilter = apiRequestData;
        columnsToShow = requestsColumns;
        titleForTable = "Requisições Pendentes";
    }
    else if (activeView === "usuarios") {
        dataToFilter = allUserData;
        columnsToShow = userColumns;
        titleForTable = "Usuários Cadastrados";
    }

    const [filteredData, setFilteredData] = useState([]);

    useEffect(() => {
        const term = searchTerm.toLowerCase();

        const filtered = dataToFilter.filter(item =>
            Object.values(item).some(
                value => String(value).toLowerCase().includes(term)
            )
        );

        setFilteredData(filtered);
        setCurrentPage(1);
    }, [searchTerm, dataToFilter]);

    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
    const currentTableData = filteredData.slice(startIndex, startIndex + ITEMS_PER_PAGE);
    const totalPages = Math.ceil(filteredData.length / ITEMS_PER_PAGE);

    return (
        <>
            <main className="perfil-page-container">

                <PerfilMenuAdm
                    activeView={activeView}
                    onNavigate={setActiveView}
                />

                {activeView === "filmes" && loadingMovies && <p className="error-states">Carregando filmes...</p>}
                {activeView === "filmes" && errorMovies && <p className="error-states">Erro: {errorMovies}</p>}

                {activeView === "requisicoes" && loadingRequests && <p className="error-states">Carregando requisições...</p>}
                {activeView === "requisicoes" && errorRequests && <p className="error-states">Erro: {errorRequests}</p>}

                <AdmListTable
                    title_table={titleForTable}
                    columns={columnsToShow}
                    data={currentTableData}
                    searchTerm={searchTerm}
                    onSearchChange={setSearchTerm}
                    currentPage={currentPage}
                    totalPages={totalPages}
                    onPageChange={setCurrentPage}
                />

            </main>
            <Footer />
        </>
    );
}
