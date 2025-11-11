import "./HomePage.css";
import Header from "../../organisms/Header/Header.jsx"
import Title from "../../atoms/Title/Title.jsx"
import HomeMoviesLine from "../../organisms/HomeMoviesLine/HomeMoviesLine.jsx";
import HomeFooterInfo from "../../organisms/HomeFooterInfo/HomeFooterInfo.jsx";
import Footer from "../../organisms/Footer/Footer.jsx"

export default function HomePage() {
    const moviesMock = [
        {
            movie_poster: "https://uauposters.com.br/media/catalog/product/5/0/508320201013-uau-posters-filmes-a-origem-inception.jpg",
            movie_name: "Inception",
            movie_review_number: "3.9",
            favorite_icon_variant: "inactive",
        },
        {
            movie_poster: "https://uauposters.com.br/media/catalog/product/4/1/411320150509-uau-posters-filmes-interestelar-interestellar.jpg",
            movie_name: "Interstellar",
            movie_review_number: "4.0",
            favorite_icon_variant: "inactive",
        },
        {
            movie_poster: "https://m.media-amazon.com/images/I/5151N2hUPiL._AC_UF894,1000_QL80_.jpg",
            movie_name: "The Dark Knight",
            movie_review_number: "4.5",
            favorite_icon_variant: "inactive",
        },
        {
            movie_poster: "https://uauposters.com.br/media/catalog/product/4/0/408320150509-uau-posters-filmes-avengers-os-vingadores.jpg",
            movie_name: "Avengers",
            movie_review_number: "4.9",
            favorite_icon_variant: "inactive",
        },
    ];

    const three = [
        {
            movie_poster: "https://wl-incrivel.cf.tsp.li/resize/728x/webp/773/72d/8f253a5a279b17e467c44cf876.jpg.webp",
            movie_name: "Inception",
            movie_review_number: "3.9",
            movie_year: "1920",
            movie_genre: "Ação"
        },
        {
            movie_poster: "https://wl-incrivel.cf.tsp.li/resize/728x/webp/ba5/8d6/2f7fca5614b7e9d4d5425600e5.jpg.webp",
            movie_name: "Interstellar",
            movie_review_number: "4.0",
            movie_year: "1920",
            movie_genre: "Ação"
        },
        {
            movie_poster: "https://wl-incrivel.cf.tsp.li/resize/728x/webp/a97/1eb/ceb0e65f54af04121f5866a7b0.jpg.webp",
            movie_name: "The Dark Knight",
            movie_year: "1920",
            movie_genre: "Ação"
        },
    ];

    return (
        <>
            <Header/>
            <main className="home-page-conteiner">
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Mais Populares"} />
                    <HomeMoviesLine movies={moviesMock} variant="four"/>
                    <HomeMoviesLine movies={moviesMock} variant="four"/>
                </section>
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Puro Cinema"}/>
                    <HomeMoviesLine movies={three} variant="horizontal"/>
                </section>   
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Histórias Reais"} />
                    <HomeMoviesLine movies={moviesMock} variant="four"/>
                    <HomeMoviesLine movies={moviesMock} variant="four"/>
                </section>
                <HomeFooterInfo login={false}/>
            </main>
            <Footer/>
        </>
    )
}