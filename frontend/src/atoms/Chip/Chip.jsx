// Chip.jsx
import "./Chip.css";

export default function Chip({
    variant,
    text_chip,
    removable = false,
    onRemove,
    onClick
}) {
    const classes = `chip chip-${variant}`;

    function handleRemove(e) {
        e.stopPropagation();
        if (onRemove) onRemove(text_chip)
    }

    return (
        <div 
            className={classes} 
            role={onClick ? "button" : undefined} 
            tabIndex={onClick ? 0 : undefined}
            onClick={() => onClick && onClick(text_chip)}
            onKeyDown={(e) => {
                if (onClick && (e.key === "Enter" || e.key === " ")) onClick(text_chip);
            }}
            aria-label={text_chip}
        >
            <span className="chip-text">{text_chip}</span>

            {removable && (
                <button
                    className="chip-remove"
                    onClick={handleRemove}
                    type="button"
                    aria-label={`Remover ${text_chip}`}
                >
                    x
                </button>
            )}
        </div>
    );
}