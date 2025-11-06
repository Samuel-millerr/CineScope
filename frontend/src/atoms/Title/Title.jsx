import "./Title.css";

export default function SubTitleHome({title, type}) {
    const classes = `title title-${type}`
    return <h2 className={classes}>{title}</h2>
}
