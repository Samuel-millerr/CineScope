const API_URL = "http://localhost:8000/api/auth";

export const authLogin = async (email, password) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify({ email, password }) 
    };

    const response = await fetch(API_URL+"/login", fetchOptions);

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        console.log(errorData)
    }

    const data = await response.json();
    return data;
}

export const authRegister = async (email, password, confirmPassword, name, lastName, user) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify({ email, password, confirmPassword, name, lastName, user }) 
    };

    const response = await fetch(API_URL+"/sing_up", fetchOptions);

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        console.log(errorData)
    }

    const data = await response.json();
    return data;
}