import "./Chip.css";

export default function Chip({ variant, chip_text, removable = false, onRemove, onClick }) {
    // Chips do site, podem ser mudados para ter algum tipo de funcionalidade, caso necessário
    const classes = `chip chip-${variant}`;

    function handleRemove(e) {
        // Função simples para evitar a propagação da função, função utilizada para remover o chip de crud
        e.stopPropagation();
        if (onRemove) onRemove(chip_text)
    }

    return (
        <div
            className={classes}
            role={onClick ? "button" : undefined}
            tabIndex={onClick ? 0 : undefined}
            onClick={() => onClick && onClick(chip_text)}
            onKeyDown={(e) => {
                if (onClick && (e.key === "Enter" || e.key === " ")) onClick(chip_text);
            }}
            aria-label={chip_text}
        >
            <span className="chip-text">{chip_text}</span>

            {removable && (
                <button className="chip-remove" onClick={handleRemove} type="button" aria-label={`Remover ${chip_text}`}>
                    x
                </button>
            )}
        </div>
    );
}