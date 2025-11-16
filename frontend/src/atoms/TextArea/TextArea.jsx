import "./TextArea.css";

export default function TextArea({ variant, htmlFor, placeholder, value, onChange }) {
  // Componente para as textareas do site, pode ser alterado de acordo com o props 'variant'
  const classes = `textarea textarea-${variant}`;
  return (
    <textarea
      name={htmlFor}
      id={htmlFor}
      className={classes}
      placeholder={placeholder}
      maxLength={255}
      value={value}
      onChange={onChange}
    ></textarea>
  );
}