import "../LoginPage/LoginPage.css"
import { Link } from "react-router-dom";
import Logo from "../../atoms/Logo/Logo";
import InputGroup from "../../molecules/InputGroup/InputGroup";
import Button from "../../atoms/Button/Button";

export default function SingUpPage({ step = "first" }) {
    if (step === "first") {
        return (
            <main className="auth-page">
                <section className="auth-page-container">
                    <div className="auth-page-container-header">
                        <Logo />
                        <p>Junte-se à comunidade CineScope e comece a cadastrar seus filmes!</p>
                    </div>
                    <form className="auth-form">
                        <InputGroup htmlFor={"user"} type={"text"} label={"E-mail"} placeholder={"Ex: seu.email@gmail.com"} variant={"white"} />
                        <InputGroup htmlFor={"password"} type={"password"} label={"Senha"} placeholder={"Digite sua senha"} variant={"white"} />
                        <InputGroup htmlFor={"confirmPassword"} type={"password"} label={"Confirme sua senha"} placeholder={"Confirme sua senha"} variant={"white"} />
                        <Link to={"/singUp-second-step"}><Button text_button={"Continuar"} variant={"blue-gradient"}/></Link>
                    </form>
                    <p>Já é parte da comunidade? <Link to={"/login"}> Faça Login </Link></p>
                </section>
            </main>
        )
    } else if (step === "second") {
        return (
            <main className="auth-page">
                <section className="auth-page-container">
                    <div className="auth-page-container-header">
                        <Logo />
                        <p>Adicione as informações faltantes para finalizar seu cadastro.</p>
                    </div>
                    <form className="auth-form">
                        <InputGroup htmlFor={"name"} type={"text"} label={"Nome"} placeholder={"Digite seu primeiro nome"} variant={"white"} />
                        <InputGroup htmlFor={"last-name"} type={"text"} label={"Sobrenome"} placeholder={"Digite seu sobrenome"} variant={"white"} />
                        <InputGroup htmlFor={"email"} type={"email"} label={"E-mail"} placeholder={"Ex: seu.email@gmail.com"} variant={"white"} />
                        <Button text_button={"Cadastro"} variant={"blue-gradient"}/>
                    </form>
                    <p>Já é parte da comunidade? <Link to={"/login"}> Faça Login </Link></p>
                </section>
            </main>
        )
    }
}