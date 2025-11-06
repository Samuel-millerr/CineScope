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

function App() {
    return (
        <>
            <Chip variant="purple" text_chip="Ação" />
            <Chip variant="dark-blue" text_chip="Comédia" />
            <Chip variant="crud" text_chip="Romance" removable={true} />
        </>
    );
}

export default App