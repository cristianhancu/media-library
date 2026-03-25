import { Link } from "react-router-dom";
import { useState } from "react";

function Register() {
    const [role, setRole] = useState("user");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Selected role:", role);
  };

  return (
    <div className="bg-blue-200 w-screen h-screen flex items-center justify-center">
      <form className="flex flex-col items-center justify-center gap-6 bg-blue-100 p-8 rounded-lg shadow-md">
        <input
          type="text"
          placeholder="Username"
          className="px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
                <input
          type="email"
          placeholder="Email"
          className="px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <input
          type="password"
          placeholder="Password"
          className="px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
         <select
          value={role}
          onChange={(e) => setRole(e.target.value)}
          className="px-4 py-1 border rounded-md align-self-start focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          <option value="user">USER</option>
          <option value="admin">ADMIN</option>
        </select>

        <button
          type="submit"
          className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors duration-300"
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