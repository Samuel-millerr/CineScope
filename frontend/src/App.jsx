import MyReviewsCard from "./molecules/MyReviewsCard/MyReviewsCard";
import "./styles/global.css";
import { useState } from "react";

export default function App() {
  const [ratingLine, setRatingLine] = useState()
  
  return (
    <>
      <MyReviewsCard movie_poster={"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIyLcRsQ_jU2U-PJjeNy8RbH7YGN-zV4ZutQ&s"} title={"ai enzao"} comment={"cuzinho"} rating={5} setLineRating={setRatingLine}/>
    </>
  );
}
