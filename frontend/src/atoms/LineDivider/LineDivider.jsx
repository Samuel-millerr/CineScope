import "./LineDivider.css";

export default function LineDivider({variant}) {
    const classes = `line-divider-${variant}`
    return <hr className={classes} />
}