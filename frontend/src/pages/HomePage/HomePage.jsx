import "./HomePage.css";
import { useState } from "react";
import { useEffect } from "react";
import { fetchMoviesSimpleInfo } from "../../services/movieService.jsx";
import Header from "../../organisms/Header/Header.jsx"
import Title from "../../atoms/Title/Title.jsx"
import HomeMoviesLine from "../../organisms/HomeMoviesLine/HomeMoviesLine.jsx";
import HomeFooterInfo from "../../organisms/HomeFooterInfo/HomeFooterInfo.jsx";
import Footer from "../../organisms/Footer/Footer.jsx"

export default function HomePage() {
    const [movies, setMovies] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadData = async () => {
            try {
                const data = await fetchMoviesSimpleInfo();
                const shuffledData = data.sort(() => Math.random() - 0.5);
                setMovies(shuffledData);
            } catch (err) {
                setError(err.message)
            } finally {
                setIsLoading(false)
            };
        }

        loadData();
    }, []);
    
    if (isLoading) {
        return <p className="error-states">Carregando filmes...</p>;
    }

    if (error) {
        return <p className="error-states">Ocorreu um erro: {error}</p>;
    }

    if (!movies) {
        return <p className="error-states">Filmes não encontrados, verifique a conexão com o servidor.</p>;
    }

    const first_slice = movies.slice(0, 4);
    const second_slice = movies.slice(4, 8);
    const third_slice = movies.slice(8, 11);
    const fourth_slice = movies.slice(11, 15);
    const fifty_slice = movies.slice(15, 19);

    return (
        <>
            <Header />
            <main className="home-page-conteiner">
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Mais Populares"} />
                    <HomeMoviesLine movies={first_slice} variant="four" />
                    <HomeMoviesLine movies={second_slice} variant="four" />
                </section>
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Puro Cinema"} />
                    <HomeMoviesLine movies={third_slice} variant="horizontal" />
                </section>
                <section className="home-page-movies-conteiner">
                    <Title variant={"home"} title={"Aclamados pela Crítica"} />
                    <HomeMoviesLine movies={fourth_slice} variant="four" />
                    <HomeMoviesLine movies={fifty_slice} variant="four" />
                </section>
                <HomeFooterInfo login={false} />
            </main>
            <Footer />
        </>
    )
}