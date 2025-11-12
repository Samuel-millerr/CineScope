import "./LoginPage.css";
import { Link } from "react-router-dom";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import Logo from "../../atoms/Logo/Logo";
import Button from "../../atoms/Button/Button";

export default function LoginPage() {
    return (
        <section className="login-page-container">
            <Logo />
            <p>Faça login para gerenciar sua coleção de filmes.</p>
            <form className="login-form">
                <InputGroup htmlFor={"e-mail"} type={"email"} label={"E-mail"} placeholder={"Ex: seu.email@gmail.com"} variant={"white"} />
                <InputGroup htmlFor={"password"} type={"password"} label={"Senha"} placeholder={"Digite sua senha"} variant={"white"} />
                <Button text_button={"Login"} variant={"blue-gradient"} />
            </form>
            <p>Não tem uma conta? <Link to={"/singUp"}> Crie uma </Link></p>
        </section>
    )
}