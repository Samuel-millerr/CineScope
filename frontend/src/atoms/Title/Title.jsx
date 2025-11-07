import "./Title.css";

export default function SubTitleHome({title, variant}) {
    const classes = `title title-${variant}`
    return <h2 className={classes}>{title}</h2>
}
