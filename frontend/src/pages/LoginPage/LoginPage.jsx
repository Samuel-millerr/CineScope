import "./LoginPage.css";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import { authLogin } from "../../services/authService.jsx";
import Logo from "../../atoms/Logo/Logo.jsx";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import Button from "../../atoms/Button/Button.jsx";
import { useAuth } from "../../AuthContext.jsx";

export default function LoginPage() {
    const navigate = useNavigate();
    const { login } = useAuth();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const userData = await authLogin(email, password);

            login(userData);

            toast.success("Login realizado com sucesso!");
            setTimeout(() => navigate("/"), 1200);

        } catch (err) {
            toast.error(err.message || "Credenciais inválidas.");
        }
    }


    return (
        <main className="auth-page">
            <ToastContainer theme="dark" autoClose={2500} />
            <section className="auth-page-container">
                <div className="auth-page-container-header">
                    <Logo />
                    <p>Faça login para gerenciar sua coleção de filmes.</p>
                </div>

                <form className="auth-form" onSubmit={handleSubmit}>
                    <InputGroup
                        htmlFor={"e-mail"}
                        type={"email"}
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

                    <Button
                        text_button={"Login"}
                        variant={"blue-gradient"}
                        type="submit"
                    />
                </form>

                <p>
                    Não tem uma conta? <Link to={"/singUp-first-step"}> Crie uma </Link>
                </p>
            </section>
        </main>
    );
}
