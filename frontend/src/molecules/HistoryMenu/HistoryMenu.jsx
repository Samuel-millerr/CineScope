import "./HistoryMenu.css";
import HistoryMenuText from "../HistoryMenuText/HistoryMenuText";
import LineDivider from "../../atoms/LineDivider/LineDivider";

export default function HistoryMenu({edition_histories}) {
    return (
        <aside className="history-menu">   
        <h2>Historico de Edições</h2>
        {   
            Object.entries(edition_histories).map(([key, history]) => (
                <>
                    <LineDivider variant={"transparent"}/>  
                    <HistoryMenuText key={key} edition_history={history}/>
                </>
            ))
        }
        </aside>
    )
}