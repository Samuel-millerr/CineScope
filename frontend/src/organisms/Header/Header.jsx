import HomeBanner from "../HomeBanner/HomeBanner";
import NavBar from "../../molecules/NavBar/NavBar.jsx";
import "./Header.css";

export default function Header() {
    return (
        <header className="header">
            <NavBar/>
            <HomeBanner/>
        </header>
    )
}