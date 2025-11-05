import "./TextArea.css";

export default function TextArea({name, variant, placeholder}) {
  const classes = `textarea textarea-${variant}`;
  return <textarea name={name} className={classes} placeholder={placeholder} maxLength={255}></textarea>;
}