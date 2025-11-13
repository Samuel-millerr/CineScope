import "./TextArea.css";

export default function TextArea({ variant, htmlFor, placeholder, value, onChange }) {
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