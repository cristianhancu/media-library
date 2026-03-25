import { Link, useNavigate } from "react-router-dom";
import { useState, type FormEvent } from "react";

function Register() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("USER");

  const navigate = useNavigate();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          email,
          password,
          role,
        }),
      });

      if (response.ok) {
        alert("User created successfully!");
        navigate("/")
      } else {
        const text = await response.text();
        alert(text);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="bg-blue-200 w-screen h-screen flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col items-center justify-center gap-6 bg-blue-100 p-8 rounded-lg shadow-md"
      >
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="px-4 py-2 border rounded-md"
        />

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="px-4 py-2 border rounded-md"
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="px-4 py-2 border rounded-md"
        />

        <select
          value={role}
          onChange={(e) => setRole(e.target.value)}
          className="px-4 py-2 border rounded-md"
        >
          <option value="USER">USER</option>
          <option value="ADMIN">ADMIN</option>
        </select>

        <button
          type="submit"
          className="px-4 py-2 bg-blue-500 text-white rounded-md"
        >
          Sign Up
        </button>

        <p className="text-sm text-center">
          Already have an account?{" "}
          <Link to="/" className="text-blue-500">
            Login
          </Link>
        </p>
      </form>
    </div>
  );
}

export default Register;