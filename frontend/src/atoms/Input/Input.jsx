import "./Input.css"

export default function Input({color, placeholder}) {
    let input; 

    if (color === "white") {
        input = <input className="input input-white" placeholder={placeholder}/>
    } else if (color === "black") {
        input = <input className="input input-black" placeholder={placeholder}/>
    }

    return input
}