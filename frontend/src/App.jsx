import "./styles/global.css";
import Star from "./atoms/Star/Star.jsx"
import Favorite from "./atoms/Favorite/Favorite.jsx"
import Logo from "./atoms/Logo/Logo.jsx"
import Input from "./atoms/Input/Input.jsx"
import TextArea from "./atoms/TextArea/TextArea.jsx"
import SearchBar from "./atoms/SearchBar/SearchBar.jsx"
import Title from "./atoms/Title/Title.jsx"
import PerfilCard from "./atoms/PerfilCard/PerfilCard.jsx"
import Button from "./atoms/Button/Button.jsx"
import SmallButton from "./atoms/SmallButton/SmallButton.jsx"
import Chip from "./atoms/Chip/Chip.jsx";

import GroupInput from "./molecules/InputGroup/InputGroup.jsx";
import TextAreaGroup from "./molecules/TextAreaGroup/TextAreaGroup.jsx";
import PerfilMenu from "./molecules/PerfilMenu/PerfilMenu.jsx";
import HistoryMenuText from "./molecules/HistoryMenuText/HistoryMenuText.jsx";
import HistoryMenu from "./molecules/HistoryMenu/HistoryMenu.jsx";

function App() {
    const edition_histories = [
        {
            "id": 1,
            "date": "20/10/2006",
            "time": "10:42",
            "user": "Samuel"
        }
    ]
    return (
        <>
            <HistoryMenu edition_histories={edition_histories}/>
        </>
    );
}

export default App