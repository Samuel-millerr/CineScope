import "./PerfilAdmPage.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import PerfilMenuAdm from "../../molecules/PerfilMenuAdm/PerfilMenuAdm.jsx";
import NavBar from "../../organisms/NavBar/NavBar.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";
import AdmListTable from "../../organisms/AdmListTable/AdmListTable.jsx";
import EditIcon from "../../assets/icons/admin-icons/edit-icon.svg";
import EyeIcon from "../../assets/icons/admin-icons/eye-icon.svg";
import TrashIcon from "../../assets/icons/admin-icons/trash-icon.svg";
import UserRemoveIcon from "../../assets/icons/admin-icons/user-remove-icon.svg";
import ApprovedIcon from "../../assets/icons/admin-icons/approved-icon.svg";
import ReprovedIcon from "../../assets/icons/admin-icons/reproved-icon.svg";

// --- SIMULAÇÃO DE DADOS DA API ---
const allMovieData = [
    { id: "#01", filme: "Título do Filme A", avaliacaoMedia: 0.0, dataAdicao: "13/05/2022", dataEdicao: "13/05/2022" },
    { id: "#02", filme: "Título do Filme B", avaliacaoMedia: 4.5, dataAdicao: "22/05/2022", dataEdicao: "22/05/2022" },
    { id: "#03", filme: "Título do Filme C", avaliacaoMedia: 3.2, dataAdicao: "15/06/2022", dataEdicao: "15/06/2022" },
    { id: "#04", filme: "Título do Filme D", avaliacaoMedia: 1.0, dataAdicao: "06/09/2022", dataEdicao: "06/09/2022" },
    { id: "#05", filme: "Título do Filme E", avaliacaoMedia: 5.0, dataAdicao: "25/09/2022", dataEdicao: "25/09/2022" },
    { id: "#06", filme: "Título do Filme F", avaliacaoMedia: 2.8, dataAdicao: "04/10/2022", dataEdicao: "04/10/2022" },
    { id: "#07", filme: "Título do Filme G", avaliacaoMedia: 3.9, dataAdicao: "17/10/2022", dataEdicao: "17/10/2022" },
    { id: "#08", filme: "Título do Filme H", avaliacaoMedia: 0.0, dataAdicao: "24/10/2022", dataEdicao: "24/10/2022" },
    { id: "#09", filme: "Título do Filme I", avaliacaoMedia: 4.1, dataAdicao: "01/11/2022", dataEdicao: "01/11/2022" },
    { id: "#10", filme: "Título do Filme J", avaliacaoMedia: 3.7, dataAdicao: "22/11/2022", dataEdicao: "22/11/2022" },
    { id: "#11", filme: "Outro Filme K", avaliacaoMedia: 2.5, dataAdicao: "01/12/2022", dataEdicao: "01/12/2022" },
    { id: "#12", filme: "Teste de Busca L", avaliacaoMedia: 4.0, dataAdicao: "05/01/2023", dataEdicao: "05/01/2023" },
];

// --- DADOS 2: REQUISIÇÕES ---
const allRequestData = [
    { id: "#R01", filme: "O Poderoso Chefão", solicitante: "bruno.costa", dataRequisicao: "14/11/2025", status: "Pendente" },
    { id: "#R02", filme: "A Origem", solicitante: "alice.silva", dataRequisicao: "13/11/2025", status: "Aprovado" },
    { id: "#R03", filme: "Pulp Fiction", solicitante: "carla.dias", dataRequisicao: "12/11/2025", status: "Recusado" },
    { id: "#R04", filme: "Interestelar", solicitante: "bruno.costa", dataRequisicao: "11/11/2025", status: "Pendente" },
    { id: "#R05", filme: "O Resgate do Soldado Ryan", solicitante: "daniel.gomes", dataRequisicao: "10/11/2025", status: "Aprovado" },
];

// --- DADOS 3: USUÁRIOS ---
const allUserData = [
    { id: "#U01", usuario: "alice.silva", nomeCompleto: "Alice Silva", dataCadastro: "01/03/2023", email: "alice@exemplo.com" },
    { id: "#U02", usuario: "bruno.costa", nomeCompleto: "Bruno Costa", dataCadastro: "05/03/2023", email: "bruno@exemplo.com" },
    { id: "#U03", usuario: "carla.dias", nomeCompleto: "Carla Dias", dataCadastro: "10/03/2023", email: "carla@exemplo.com" },
    { id: "#U04", usuario: "daniel.gomes", nomeCompleto: "Daniel Gomes", dataCadastro: "15/03/2023", email: "daniel@exemplo.com" },
    { id: "#U05", usuario: "elisa.fernandes", nomeCompleto: "Elisa Fernandes", dataCadastro: "20/03/2023", email: "elisa@exemplo.com" },
];
const ITEMS_PER_PAGE = 10;

export default function AdminDashboard() {
    const [activeView, setActiveView] = useState("filmes")
    const [searchTerm, setSearchTerm] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const [filteredData, setFilteredData] = useState([]);

    // Chamada da função para a remoção do filme do site
    const handleDeleteMovie = (movieId) => {
        if (window.confirm(`Tem certeza que deseja deletar o filme ${movieId}?`)) {
            alert(`Deletar Filme: ${movieId}`);
        }
    };

    const handleDeleteUser = (userId) => {
        if (window.confirm(`Tem certeza que deseja deletar o usuario ${userId}?`)) {
            alert(`Deletar Usuário: ${userId}`);
        }
    }

    // Definição das colunas de cada uma das tables
    const movieColumns = [
        { label: "Filme ID", accessor: "id" },
        { label: "Filme", accessor: "filme" },
        { label: "Avaliação Média", accessor: "avaliacaoMedia", className: "rating-cell" },
        { label: "Data de Adição", accessor: "dataAdicao" },
        { label: "Data de Edição", accessor: "dataEdicao" },
        {
            label: "Ações",
            accessor: "acoes",
            className: "action-buttons",
            render: (row) => (
                <>
                    <Link to={"/movie"}>
                        <figure>
                            <img src={EyeIcon} alt="Ícone de visualização" />
                        </figure>
                    </Link>
                    <Link to={"/edit-movie"}>
                        <figure>
                            <img src={EditIcon} alt="Ícone de edição" />
                        </figure>
                    </Link>
                    <a title="Deletar" onClick={() => handleDeleteMovie(row.id)}>
                        <figure>
                            <img src={TrashIcon} alt="Ícone de remoção" />
                        </figure>
                    </a>
                </>
            )
        }
    ]

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
                    <a title="Aceitar">
                        <figure>
                            <img src={ApprovedIcon} alt="Ícone de remoção de usuário" />
                        </figure>
                    </a>
                    <a title="Recusar">
                        <figure>
                            <img src={ReprovedIcon} alt="Ícone de remoção de usuário" />
                        </figure>
                    </a>
                </>
            )
        }
    ]

    const userColumns = [
        { label: "Usuário ID", accessor: "id" },
        { label: "Usuário", accessor: "usuario" },
        { label: "Nome Completo", accessor: "nomeCompleto" },
        { label: "Data de Cadastro", accessor: "dataCadastro" },
        { label: "E-mail", accessor: "email" },
        {
            label: "Ações",
            accessor: "acoes",
            className: "action-buttons",
            render: (row) => (
                <a title="Deletar" onClick={() => handleDeleteUser(row.id)}>
                    <figure>
                        <img src={UserRemoveIcon} alt="Ícone de remoção de usuário" />
                    </figure>
                </a>
            )
        }
    ]

    let dataToFilter;
    let columnsToShow;
    let titleForTable;

    if (activeView === 'filmes') {
        dataToFilter = allMovieData;
        columnsToShow = movieColumns;
        titleForTable = "Filmes Cadastrados";
    } else if (activeView === 'requisicoes') {
        dataToFilter = allRequestData;
        columnsToShow = requestsColumns;
        titleForTable = "Requisições de Filmes";
    }
    else if (activeView === 'usuarios') {
        dataToFilter = allUserData;
        columnsToShow = userColumns;
        titleForTable = "Usuários Cadastrados";
    }
    useEffect(() => {
        const lowerCaseSearchTerm = searchTerm.toLowerCase();

        const dataFiltered = dataToFilter.filter(item => {
            const idMatch = item.id.toLowerCase().includes(lowerCaseSearchTerm);
            const movieMatch = item.filme ? item.filme.toLowerCase().includes(lowerCaseSearchTerm) : false;
            const userMatch = item.nome ? item.nome.toLowerCase().includes(lowerCaseSearchTerm) : false;
            return idMatch || movieMatch || userMatch;
        });
        setFilteredData(dataFiltered);
        setCurrentPage(1);
    }, [searchTerm, dataToFilter]);

    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
    const endIndex = startIndex + ITEMS_PER_PAGE;
    const currentTableData = filteredData.slice(startIndex, endIndex);
    const totalPages = Math.ceil(filteredData.length / ITEMS_PER_PAGE);

    return (
        <>
            <NavBar />
            <main className="perfil-page-container">
                <PerfilMenuAdm
                    activeView={activeView}
                    onNavigate={setActiveView}
                />

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
    )
}