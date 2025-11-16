import "./Title.css";

export default function Title({title, variant}) {
    // Componente para a criação dos diferentes e repetidos titulos do site
    const classes = `title title-${variant}`
    return <h2 className={classes}>{title}</h2>
}
