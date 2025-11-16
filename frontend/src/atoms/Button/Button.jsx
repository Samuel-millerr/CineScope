import "./Button.css";

export default function Button({ type = "submit", variant, text_button, onClick }) {
  // Componente simples de botão, permite a variação entre cores
  const classes = `button button-${variant}`;
  return (
    <button className={classes} type={type} onClick={onClick}>
      {text_button}
    </button>
  );
}