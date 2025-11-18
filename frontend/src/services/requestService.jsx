const API_URL = "http://localhost:8000/api/requests";

export async function fetchPendingRequests() {
    const res = await fetch(`${API_URL}?filter=pending`);
    if (!res.ok) throw new Error("Erro ao buscar requisições.");
    return res.json();
}

export async function approveRequest(id) {
    const token = localStorage.getItem("token");

    const res = await fetch(`${API_URL}/allow/${id}`, {
        method: "PUT",
        headers: { Authorization: `Bearer ${token}` },
    });

    if (!res.ok) throw new Error("Erro ao aprovar requisição.");
    return res.json();
}

export async function denyRequest(id) {
    const token = localStorage.getItem("token");

    const res = await fetch(`${API_URL}/deny/${id}`, {
        method: "PUT",
        headers: { Authorization: `Bearer ${token}` },
    });

    if (!res.ok) throw new Error("Erro ao negar requisição.");
    return res.json();
}
