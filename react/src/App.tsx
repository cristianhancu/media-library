import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";  
import Home from "./pages/Home";
import Users from "./pages/Users";
import Media from "./pages/Media";

import "./index.css";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/dashboard" element={<Dashboard />}>
        <Route path="home" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />}></Route>
        <Route path="users" element={<Users />} />
        <Route path="/dashboard" element={<Dashboard />}></Route>
        <Route path="media" element={<Media />} />

  </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;