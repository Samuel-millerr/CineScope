import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [auth, setAuth] = useState({
        token: null,
        role: null,
        user: null
    });

    useEffect(() => {
        const token = localStorage.getItem("token");
        const role = localStorage.getItem("userType");
        const user = localStorage.getItem("user");

        if (token)
            setAuth({ token, role, user });
    }, []);

    const login = (data) => {
        localStorage.setItem("token", data.token);
        localStorage.setItem("userType", data.user_role);
        localStorage.setItem("user", data.user);

        setAuth({
            token: data.token,
            role: data.user_role,
            user: data.user
        });
    };

    const logout = () => {
        localStorage.clear();
        setAuth({
            token: null,
            role: null,
            user: null
        });
    };

    return (
        <AuthContext.Provider value={{ auth, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
