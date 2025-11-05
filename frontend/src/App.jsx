import "./styles/global.css"
import Star from "./atoms/Star/Star.jsx"
import Favorite from "./atoms/Favorite/Favorite.jsx"
import Logo from "./atoms/Logo/Logo.jsx"
import Input from "./atoms/Input/Input.jsx"
import TextArea from "./atoms/TextArea/TextArea.jsx"
import SearchBar from "./atoms/SearchBar/SearchBar.jsx"
import Title from "./atoms/Title/Title.jsx"
import PerfilCard from "./atoms/PerfilCard/PerfilCard.jsx"

function App() {
    return (
        <>
            <Star type={"half"}/>
            <Favorite type={"active"}/>
            <Favorite type={"inactive"}/>
            <Logo/>
            <Input variant={"white"} placeholder={"input"}/>
            <Input variant={"black"} placeholder={"input"}/>
            <PerfilCard number_statistic={0} name_statistic={"Filmes assistidos"}/>
        </>
    )
}

export default App