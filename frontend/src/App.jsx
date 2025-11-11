import "./styles/global.css";
import Title from "./atoms/Title/Title.jsx";
import NavBar from "./organisms/NavBar/NavBar.jsx"
import HomeBanner from "./organisms/HomeBanner/HomeBanner.jsx";


export default function App() { 
  return (
    <>
      <NavBar/>
      <HomeBanner/>
    </>
  );
}
