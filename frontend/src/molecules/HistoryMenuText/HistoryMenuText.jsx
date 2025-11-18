import "./HistoryMenuText.css";

export default function HistoryMenuText({edition_history}) {
    // Texto para o menu de edição, recebe a linha de edição específica feita pelo map, e passa para a linha
    const {date, user} = edition_history
    return (
            <div className="history-menu-text-conteiner">
                <p>{date}</p>
                <p>{user}</p>
            </div>
    )
}