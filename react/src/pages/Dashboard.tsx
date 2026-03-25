import { Link, useNavigate } from "react-router-dom";


function Dashboard() { 
    const navigate = useNavigate();

    return (
<div className="bg-gray-900 w-80 h-screen flex flex-col justify-between">

  <div>
    <div className="px-4 py-4">
      <h1 className="text-3xl font-bold text-white">
        Welcome, {localStorage.getItem("username")}!
      </h1>
    </div>

    <aside className="text-white px-4">
      <ul className="space-y-2 font-medium">
        <li>
          <a className="flex items-center text-xl font-serif px-2 py-1.5 hover:bg-gray-700 rounded">
            <span className="ms-3">home</span>
          </a>
        </li>
        <li>
          <a className="flex items-center text-xl font-serif px-2 py-1.5 hover:bg-gray-700 rounded">
            <span className="ms-3">users</span>
          </a>
        </li>
      </ul>
    </aside>
  </div>

  <div className="px-4 py-4">
    <button
      className="w-full text-left px-2 py-1.5 hover:bg-gray-700 rounded text-white"
      onClick={() => {
        localStorage.removeItem("username");
         navigate("/");
      }}
    >
      Sign out
    </button>
  </div>

</div>
);
}

export default Dashboard;