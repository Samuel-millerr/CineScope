import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage/HomePage.jsx";
import LoginPage from "./pages/LoginPage/LoginPage.jsx";
import SingUpPage from "./pages/SingUpPage/SingUpPage.jsx";
import SearchPage from "./pages/SearchPage/SearchPage.jsx";
import MoviePage from "./pages/MoviePage/MoviePage.jsx";
import MovieAddPage from "./pages/MovieAddPage/MovieAddPage.jsx";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<HomePage/>}/>
      <Route path="/login" element={<LoginPage/>}/>
      <Route path="/singUp-first-step" element={<SingUpPage step="first"/>}/>
      <Route path="/singUp-second-step" element={<SingUpPage step="second"/>}/>
      <Route path="/search" element={<SearchPage/>}/>
      <Route path="/movie" element={<MoviePage/>}/>
      <Route path="/add-movie" element={<MovieAddPage/>}/>
    </Routes>
  );
}
