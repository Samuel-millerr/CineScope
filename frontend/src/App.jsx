import "./styles/global.css";
import CardActor from "./molecules/CardActor/CardActor";
import Chip from "./atoms/Chip/Chip.jsx"
import RequestCard from "./molecules/RequestCard/RequestCard.jsx";
import AboutUsCard from "./molecules/AboutUsCard/AboutUsCard.jsx";
import TransparencyIcon from "./assets/icons/transparency-icon.svg"
import PerfilMovieCard from "./molecules/PerfilMovieCard/PerfilMovieCard.jsx";
import RelatedMovieCard from "./molecules/RelatedMovieCard/RelatedMovieCard.jsx";

function App() {
    return (
        <>  
            <RelatedMovieCard 
                movie_poster={"https://img.elo7.com.br/product/zoom/2368C5D/big-poster-filme-marvel-venom-tamanho-90x60-cm-loot-op-010-geek.jpg"}
                movie_name={"PiroquicusJunior"}
                movie_review_number={4.8}
                movie_year={"1994"}
                movie_genre={"Ação"}
            />
        </>
    );
}

export default App