import './YearRangeFilter.css';
import { useState, useEffect, useCallback } from 'react';

export default function YearRangeFilter({ title, min, max, onChange }) {
    // Componente para o filtro do tipo slider na aba de filtros da tela de pesquisa
    const [minVal, setMinVal] = useState(min);
    const [maxVal, setMaxVal] = useState(max);

    const minPosPercent = ((minVal - min) / (max - min)) * 100;
    const maxPosPercent = ((maxVal - min) / (max - min)) * 100;

    const handleChange = useCallback(() => {
        onChange({ min: minVal, max: maxVal });
    }, [minVal, maxVal, onChange]);

    useEffect(() => {
        const timer = setTimeout(() => {
            handleChange();
        }, 200);
        return () => clearTimeout(timer);
    }, [minVal, maxVal, handleChange]);

    const handleMinChange = (e) => {
        const value = Math.min(Number(e.target.value), maxVal - 1);
        setMinVal(value);
    };

    const handleMaxChange = (e) => {
        const value = Math.max(Number(e.target.value), minVal + 1);
        setMaxVal(value);
    };

    return (
        <div className="year-range-filter-container">
            <div className="year-range-filter-header">
                <h3>{title}</h3>
                <div className="year-range-filter-values">
                    {minVal} - {maxVal}
                </div>
            </div>

            <div className="slide-wrapper">
                <div className="slide-track"></div>
                <div
                    className="slide-track-process"
                    style={{ left: `${minPosPercent}%`, right: `${100 - maxPosPercent}%` }}
                ></div>
                <div className="range-inputs">
                    <input
                        type="range"
                        min={min}
                        max={max}
                        value={minVal}
                        onChange={handleMinChange}
                        className="year-range-filter-slider"
                        aria-label="Ano mínimo"
                    />
                    <input
                        type="range"
                        min={min}
                        max={max}
                        value={maxVal}
                        onChange={handleMaxChange}
                        className="year-range-filter-slider"
                        aria-label="Ano máximo"
                    />
                </div>
            </div>
        </div>
    );
};

