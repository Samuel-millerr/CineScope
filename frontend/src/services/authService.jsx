const API_URL = "http://localhost:8000/api/auth";

export const authLogin = async (email, password) => {
    try {
        const response = await fetch(`${API_URL}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();

        if (!response.ok) throw new Error(data.error || "Erro de login");

        return data;
    } catch (err) {
        throw err;
    }
};

export const authRegister = async (userBody) => {
    try {
        const response = await fetch(`${API_URL}/sing_up`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userBody)
        });

        const data = await response.json();

        if (!response.ok) throw new Error(data.error || "Erro no cadastro");

        return data;
    } catch (err) {
        throw err;
    }
};
