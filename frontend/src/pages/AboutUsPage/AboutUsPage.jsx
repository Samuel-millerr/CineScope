import "./AboutUsPage.css";
import Footer from "../../organisms/Footer/Footer";
import Title from "../../atoms/Title/Title";
import AboutUsCard from "../../molecules/AboutUsCard/AboutUsCard.jsx";
import TransparencyIcon from "../../assets/icons/transparency-icon.svg";
import CommunityIcon from "../../assets/icons/community-icon.svg";
import CheckIcon from "../../assets/icons/check-icon.svg";
import CinemaIcon from "../../assets/icons/cinema-icon.svg";
import InputGroup from "../../molecules/InputGroup/InputGroup.jsx";
import TextAreaGroup from "../../molecules/TextAreaGroup/TextAreaGroup";
import Button from "../../atoms/Button/Button";

const HeroSection = () => (
    <section className="about-us-hero">
        <div className="about-us-hero-background"></div>
        <div className="about-us-hero-content">
            <h1>Sobre Nós</h1>
            <p>
                Bem-vindo ao CineScope, o seu universo particular de cinema. Nascemos da paixão que move cinéfilos de todo o mundo:
                a vontade de descobrir, discutir e celebrar a sétima arte. Mais do que uma plataforma de avaliação, somos uma comunidade
                vibrante construída com transparência e credibilidade. Nossa missão é conectar pessoas através de histórias e criar um
                espaço autêntico para que sua voz seja ouvida.
            </p>
        </div>
    </section>
);

const ValuesSection = () => (
    <section className="about-us-values-section">
        <Title title={"Milhares de filmes para você avaliar"} variant={"about-us"} />
        <div className="movie-simple-banner"></div>
        <Title title={"Valores"} variant={"about-us"} />
        <div className="values-grid">
            <AboutUsCard value_icon={TransparencyIcon} value_title={"Transparência"} value_text={"Avaliações honestas e dados claros. Sem filtros, apenas a opinião real da comunidade."} />
            <AboutUsCard value_icon={CommunityIcon} value_title={"Comunidade"} value_text={"Um espaço seguro e inclusivo para todos os fãs. Conecte-se, debata e descubra com outros apaixonados."} />
            <AboutUsCard value_icon={CheckIcon} value_title={"Credibilidade"} value_text={"Nosso compromisso é com a veracidade. Contamos com um sistema robusto para garantir avaliações autênticas."} />
            <AboutUsCard value_icon={CinemaIcon} value_title={"Cinéfolos"} value_text={"Somos cinéfolos, assim como você. Cada recurso da plataforma é pensado e construído de fã para fã."} />
        </div>
    </section>
);

const ContactSection = () => (
    <section className="about-us-contact-section">
        <Title title={"Entre em contato conosco"} variant={"about-us"} />
        <form className="contact-form">
            <div className="form-group">
                <InputGroup htmlFor={"name"} label={"Nome"} placeholder={"Digite seu nome"} variant={"white"} />
            </div>
            <div className="form-group">
                <InputGroup type="email" htmlFor={"email"} label={"E-mail"} placeholder={"Digite seu e-mail"} variant={"white"} />

            </div>
            <div className="form-group">
                <InputGroup htmlFor={"subject"} label={"Assunto"} placeholder={"Digite o assunto da mensagem"} variant={"white"} />

            </div>
            <div className="form-group">
                <TextAreaGroup htmlFor={"name"} label={"Nome"} placeholder={"Digite sua mensagem..."} variant={"white"} />

            </div>
            <Button variant={"purple"} text_button={"Enviar"} />
        </form>
    </section>
);

export default function SobreNos() {
    return (
        // Conjunto dos elementos da página sobre nós
        <>
            <main className="about-us-container">
                <HeroSection />
                <ValuesSection />
                <ContactSection />
            </main>
            <Footer />
        </>
    );
}