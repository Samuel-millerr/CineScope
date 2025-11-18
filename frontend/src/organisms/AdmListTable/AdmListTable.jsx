import { Link } from "react-router-dom";
import Button from "../../atoms/Button/Button.jsx";
import "./AdmListTable.css";

export default function AdmListTable({ title_table, columns, data, searchTerm, onSearchChange, currentPage, totalPages, onPageChange }) {
    // Componente da tela de administrador para demonstrar as tableas
    if (!data || data.length === 0) {
        return (
            <section className="table-container">
                <h1>{title_table}</h1>
                <div className="table-header-actions">
                    <div className="table-search-input">
                        <input type="text" placeholder="Procurar..." value={searchTerm} onChange={(e) => onSearchChange(e.target.value)} />
                    </div>
                    {
                        title_table === "Filmes Cadastrados" && (
                            <Link to={"/add-movie"}>
                                <Button text_button={"Adicionar Filme"} variant={"purple"} />
                            </Link>
                        )
                    }
                </div>
                <p className="error-states"> Nenhum dado para exibir ou encontrado com a busca. </p>
            </section>
        );
    }

    return (
        <section className="table-container">
            <h1>{title_table}</h1>

            <div className="table-header-actions">
                <div className="table-search-input">
                    <input type="text" placeholder="Procurar..." value={searchTerm} onChange={(e) => onSearchChange(e.target.value)} />
                </div>
                {
                    title_table === "Filmes Cadastrados" && (
                        <Link to={"/add-movie"}>
                            <Button text_button={"Adicionar Filme"} variant={"purple"} />
                        </Link>
                    )
                }
            </div>

            <table className="data-table">
                <thead>
                    <tr>
                        {columns.map((col) => (
                            <th key={col.accessor}>{col.label}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {data.map((row) => (
                        <tr key={row.id}>
                            {columns.map((col) => (
                                <td key={col.accessor} className={col.className || ""}>
                                    {col.render ? col.render(row) : row[col.accessor]}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="pagination">
                <button onClick={() => onPageChange(currentPage - 1)} disabled={currentPage === 1}> Anterior </button>
                {Array.from({ length: totalPages }, (_, i) => i + 1).map((pageNumber) => (
                    <button
                        key={pageNumber}
                        className={`page-number ${pageNumber === currentPage ? 'active' : ''}`}
                        onClick={() => onPageChange(pageNumber)}
                    >
                        {pageNumber}
                    </button>
                ))}
                <button onClick={() => onPageChange(currentPage + 1)} disabled={currentPage === totalPages}> Próximo</button>
            </div>
        </section>
    );
}