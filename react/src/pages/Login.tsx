import { useState, useEffect, type FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function Login() {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("username");
  }, []);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const user = await response.json();

        localStorage.setItem("username", user.username);

        navigate("/dashboard");
      } else {
        alert("Username sau parola gresita");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-red-200">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col items-center gap-6 bg-white p-8 rounded-lg shadow-md"
      >
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="px-4 py-2 border rounded-md"
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="px-4 py-2 border rounded-md"
        />

        <button
          type="submit"
          className="px-4 py-2 bg-blue-500 text-white rounded-md"
        >
          Login
        </button>
                <p className="text-sm text-center">
            Don't have an account?{" "}
            <Link to="/register" className="text-blue-500">
            Sign Up
            </Link>
        </p>
      </form>
    </div>
  );
}

export default Login;