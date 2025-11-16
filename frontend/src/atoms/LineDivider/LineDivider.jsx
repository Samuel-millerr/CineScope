import "./LineDivider.css";

export default function LineDivider({ variant }) {
    // Componente para seáração, usado em páginas específicas
    const classes = `line-divider-${variant}`
    return <hr className={classes} />
}