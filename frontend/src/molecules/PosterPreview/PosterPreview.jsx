import './PosterPreview.css';
import { useState, useEffect } from 'react';

export default function PosterPreview({ posterUrl }) {
    // Componenete usado para mostrar ao usuário qual o poster está sendo colocado ou editado no filme
    const [hasError, setHasError] = useState(false);

    useEffect(() => {
        setHasError(false);
    }, [posterUrl]);

    const renderContent = () => {
        // Função utilizada para renderizar a imagem quando for encontrada
        if (posterUrl && !hasError) {
            return (
                <figure className="poster-preview-image-container">
                    <img
                        src={posterUrl}
                        alt="Preview do poster"
                        className="poster-preview-image"
                        onError={() => setHasError(true)}
                    />
                </figure>
            );
        }

        let message = "Poster Preview";
        if (hasError) {
            // Caso nenhum mensagem seja encontrada é passado uma informação ao usuário
            message = "Imagem não encontrada.";
        }

        return (
            <div className="poster-preview-placeholder">
                <p>{message}</p>
            </div>
        );
    };

    return (
        <div className="poster-preview-container">
            {renderContent()}
        </div>
    );
}