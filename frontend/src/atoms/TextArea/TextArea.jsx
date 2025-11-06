import "./TextArea.css";

export default function TextArea({variant, htmlFor, placeholder}) {
  const classes = `textarea textarea-${variant}`;
  return <textarea name={htmlFor} id={htmlFor} className={classes} placeholder={placeholder} maxLength={255}></textarea>;
}