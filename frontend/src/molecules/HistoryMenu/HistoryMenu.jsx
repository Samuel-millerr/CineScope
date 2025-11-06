import "./HistoryMenu.css";
import HistoryMenuText from "../HistoryMenuText/HistoryMenuText";

export default function HistoryMenu({edition_histories}) {
    return (
        <aside className="history-menu">    
        {
            history.map(item => (
                <HistoryMenuText key={history.id} edition_history={edition_histories}/>
            ))
        }
        </aside>
    )
}