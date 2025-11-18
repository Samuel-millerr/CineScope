import "../LoginPage/LoginPage.css";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import { authRegister } from "../../services/authService";
import Logo from "../../atoms/Logo/Logo";
import InputGroup from "../../molecules/InputGroup/InputGroup";
import Button from "../../atoms/Button/Button";

export default function SingUpPage({ step = "first" }) {
    const navigate = useNavigate();
    const location = useLocation();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("")

    const handleStep1Submit = (event) => {
        event.preventDefault();

        if (!email || !password || !confirmPassword) {
            toast.error("Por favor, preencha todos os campos.");
            return;
        }
        if (password !== confirmPassword) {
            toast.error("As senhas não conferem!");
            return;
        }
        if (password.length < 6) {
            toast.error("A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        navigate("/singUp-second-step", {
            state: { email, password }
        });
    };

    const handleFinalSubmit = async (event) => {
        event.preventDefault();

        const { email: emailStep1, password: passwordStep1 } = location.state || {};

        if (!emailStep1 || !passwordStep1) {
            toast.error("Erro! Por favor, comece o cadastro do início.");
            navigate("/singUp-first-step");
            return;
        }

        if (!firstName || !lastName || !username) {
            toast.error("Por favor, preencha todos os campos.");
            return;
        }

        const userData = {
            email: emailStep1,
            password: passwordStep1,
            firstName,
            lastName,
            user: username
        };

        await authRegister(userData);

        try {
            console.log("Dados a enviar: ", userData)
            await authRegister(userData);
            toast.success("Cadastro realizado com sucesso! Faça o login.");
            setTimeout(() => { navigate("/login"); }, 1250);

        } catch (err) {
            toast.error("Não foi possível realizar o cadastro.");
        }
    };

    if (step === "first") {
        return (
            <main className="auth-page">
                <ToastContainer theme="dark" autoClose={2500} />
                <section className="auth-page-container">
                    <div className="auth-page-container-header">
                        <Logo />
                        <p>Junte-se à comunidade CineScope e comece a cadastrar seus filmes!</p>
                    </div>
                    <form className="auth-form" onSubmit={handleStep1Submit}>
                        <InputGroup
                            htmlFor={"email"}
                            label={"E-mail"}
                            placeholder={"Ex: seu.email@gmail.com"}
                            variant={"white"}
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <InputGroup
                            htmlFor={"password"}
                            type={"password"}
                            label={"Senha"}
                            placeholder={"Digite sua senha"}
                            variant={"white"}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <InputGroup
                            htmlFor={"confirmPassword"}
                            type={"password"}
                            label={"Confirme sua senha"}
                            placeholder={"Confirme sua senha"}
                            variant={"white"}
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                        <Button type={"submit"} text_button={"Continuar"} variant={"blue-gradient"} />
                    </form>
                    <p>Já é parte da comunidade? <Link to={"/login"}> Faça Login </Link></p>
                </section>
            </main>
        )
    } else if (step === "second") {
        return (
            <main className="auth-page">
                <ToastContainer theme="dark" autoClose={2500} />
                <section className="auth-page-container">
                    <div className="auth-page-container-header">
                        <Logo />
                        <p>Adicione as informações faltantes para finalizar seu cadastro.</p>
                    </div>
                    <form className="auth-form" onSubmit={handleFinalSubmit}>
                        <InputGroup
                            htmlFor={"name"}
                            type={"text"}
                            label={"Nome"}
                            placeholder={"Digite seu primeiro nome"}
                            variant={"white"}
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                        />
                        <InputGroup
                            htmlFor={"last-name"}
                            type={"text"}
                            label={"Sobrenome"}
                            placeholder={"Digite seu sobrenome"}
                            variant={"white"}
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                        />
                        <InputGroup
                            htmlFor={"user"}
                            type={"text"}
                            label={"Usuário"}
                            placeholder={"Ex: j7.silva"}
                            variant={"white"}
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <Button type={"submit"} text_button={"Finalizar Cadastro"} variant={"blue-gradient"} />
                    </form>
                    <p>Já é parte da comunidade? <Link to={"/login"}> Faça Login </Link></p>
                </section>
            </main>
        )
    }
}