import { useState } from "react";
import "./HomeMovieCard.css";
import Favorite from "../../atoms/Favorite/Favorite.jsx";
import Star from "../../atoms/Star/Star.jsx";

export default function HomeMovieCard({ movie_poster, movie_name, movie_review_number, favorite_icon_variant = "inactive" }) {
    const [favorite, setFavorite] = useState(favorite_icon_variant);

    const handleFavoriteClick = (e) => {
        e.stopPropagation();
        setFavorite(favorite === "active" ? "inactive" : "active");
    };

    return (
        <article className="home-movie-card">
            <figure className="home-movie-card-image-container">
                <img src={movie_poster} alt={`Poster do filme ${movie_name}`} />
            </figure>

            <div className="home-movie-card-info">
                <h3>{movie_name}</h3>

                <div className="home-movie-info-bottom">
                    <figure className="home-movie-rating">
                        <Star variant="full" />
                        <p>{movie_review_number}</p>
                    </figure>

                    <div onClick={handleFavoriteClick}>
                        <Favorite variant={favorite} />
                    </div>
                </div>
            </div>
        </article>
    );
}
