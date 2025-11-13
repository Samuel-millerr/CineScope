import { Link } from "react-router-dom";
import "./Breadcrumb.css";

export default function Breadcrumb({ items }) {
  const itemEntries = Object.entries(items);
  const totalItems = itemEntries.length;

  return (
    <nav className="breadcrumb">
      {itemEntries.map(([label, path], index) => {
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