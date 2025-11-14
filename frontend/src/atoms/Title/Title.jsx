import "./Title.css";

export default function Title({title, variant}) {
    const classes = `title title-${variant}`
    return <h2 className={classes}>{title}</h2>
}
