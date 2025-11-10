import { Link } from "react-router-dom";
import "./Breadcrumb.css";

export default function Breadcrumb({ items }) {
  return (
    <nav className="breadcrumb">
      {items.map((item, index) => (
        <span key={index} className="breadcrumb-item">
          {item.path ? (
            <Link to={item.path}>{item.label}</Link>
          ) : (
            <a>{item.label}</a>
          )}
          {index < items.length - 1 && <span className="breadcrumb-separator">›</span>}
        </span>
      ))}
    </nav>
  );
}
