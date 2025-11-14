import "./Input.css";

export default function Input({ variant, type = "text", htmlFor, placeholder, value, onChange }) {
  const classes = `input input-${variant}`;
  return (
    <input
      type={type}
      name={htmlFor}
      id={htmlFor}
      className={classes}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      min={type === "number" ? 1900 : undefined}
      max={type === "number" ? 2025 : undefined}
    />
  );
}