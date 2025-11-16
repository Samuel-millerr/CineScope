/* Os arquivos de rota tem como objetivo centralizar todos os caminhos possíveis que o usuários pode utilizar dentro do site,
aqui é importado todos os documentos e definido qual o caminho eles utilizaram */
import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage/HomePage.jsx";
import LoginPage from "./pages/LoginPage/LoginPage.jsx";
import SingUpPage from "./pages/SingUpPage/SingUpPage.jsx";
import SearchPage from "./pages/SearchPage/SearchPage.jsx";
import MoviePage from "./pages/MoviePage/MoviePage.jsx";
import MovieAddPage from "./pages/MovieAddPage/MovieAddPage.jsx";
import MovieEditionPage from "./pages/MovieEditionPage/MovieEditionPage.jsx";
import PerfilPage from "./pages/PerfilPage/PerfilPage.jsx";
import PerfilProfile from "./organisms/PerfilProfile/PerfilProfile.jsx";
import PerfilCollection from "./organisms/PerfilCollection/PerfilCollection.jsx";
import PerfilMyReviews from "./organisms/PerfilMyReviews/PerfilMyReviews.jsx";
import PerfilAddEditMovie from "./organisms/PerfilAddEditMovie/PerfilAddEditMovie.jsx";
import PerfilMyRequests from "./organisms/PerfilMyRequests/PerfilMyRequests.jsx";
import PerfilAdmPage from "./pages/PerfilAdmPage/PerfilAdmPage.jsx";
import AboutUsPage from "./pages/AboutUsPage/AboutUsPage.jsx";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/singUp-first-step" element={<SingUpPage step="first" />} />
      <Route path="/singUp-second-step" element={<SingUpPage step="second" />} />
      <Route path="/search" element={<SearchPage />} />
      <Route path="/movie" element={<MoviePage />} />
      <Route path="/add-movie" element={<MovieAddPage />} />
      <Route path="/edit-movie" element={<MovieEditionPage />} />
      <Route path="/perfil" element={<PerfilPage />}>
        <Route index element={<PerfilProfile />} />
        <Route path="my-collection" element={<PerfilCollection />} />
        <Route path="my-reviews" element={<PerfilMyReviews />} />
        <Route path="add-edit-movie" element={<PerfilAddEditMovie/>}/>
        <Route path="my-requests" element={<PerfilMyRequests/>}/>
      </Route>
      <Route path="/perfil-adm" element={<PerfilAdmPage/>}/>
      <Route path="/about-us" element={<AboutUsPage/>}/>
    </Routes>
  );
}
