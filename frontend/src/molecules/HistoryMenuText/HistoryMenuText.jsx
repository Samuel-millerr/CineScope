import "./HistoryMenuText.css";

export default function HistoryMenuText({edition_history}) {
    console.log(edition_history)
    const {date, time, user} = edition_history
    return (
            <div className="history-menu-text-conteiner">
                <p>{date}</p>
                <p>{time}</p>
                <p>{user}</p>
            </div>
    )
}