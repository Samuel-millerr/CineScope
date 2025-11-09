import Star from "../../atoms/Star/Star.jsx"

export default function StarRatingLine ({rating, isSelected}) {
    const fullStars = rating;
    const emptyStars = 5 - rating;

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