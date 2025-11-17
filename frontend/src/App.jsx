import AppRoutes from "./routes.jsx";
import ScrollToTop from "./utils/ScrollToTop.jsx";
import NavBar from "./organisms/NavBar/NavBar.jsx";

export default function App() {
  return (
    <>
      <ScrollToTop/>
      <NavBar/>
      <AppRoutes/>
    </>
  );
}
