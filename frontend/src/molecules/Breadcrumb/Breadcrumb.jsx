import { Link } from "react-router-dom";
import "./Breadcrumb.css";

export default function Breadcrumb({ items }) {
  /* Função para retornar um breadcrumb funcional. O componenete recebe uma lista de valores/dicionario/objeto
  e faz um map para alinhados em linha reta e passar o caminho específico que a parte do breadcrum precisa levar o usuário */
  const itemEntries = Object.entries(items);
  const totalItems = itemEntries.length;

  return (
    <nav className="breadcrumb">
      {itemEntries.map(([label, path], index) => {
        // Faz um map simples para colocar os itens do objeto corretamente no breadcrumb
        const isLastItem = index === totalItems - 1;

        return (
          <span key={label} className="breadcrumb-item">
            {isLastItem || !path ? (
              <a data-current="true" aria-current="page">{label}</a>
            ) : (
              <Link to={path}>{label}</Link>
            )}

            {!isLastItem && <a className="breadcrumb-separator">›</a>}
          </span>
        );
      })}
    </nav>
  );
}