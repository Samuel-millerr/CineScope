import "./RatingFilterLine.css";
import Star from "../../atoms/Star/Star.jsx";

export default function RatingFilterLine ({rating = "0.0", isSelected}) {
    const roundedRating = Math.round(rating);

    const fullStars = roundedRating;
    const emptyStars = 5 - roundedRating;

    return (
        <div className={`rating-star-row ${isSelected ? 'selected' : ''}`}>
            {[...Array(fullStars)].map((_, index) => (
                <Star className={"stars"} key={`full-${index}`} variant="full" />
            ))}
            {[...Array(emptyStars)].map((_, index) => (
                <Star className={"stars"} key={`empty-${index}`} variant="empty" />
            ))}
        </div>
    );
};