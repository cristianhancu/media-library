import { Link } from "react-router-dom";

function Dashboard() {

  return (
    <div>
    <div className=" flex justify-center items-center py-20">
      <h1 className="text-3xl font-bold text-white  flex justify-center">Welcome, {localStorage.getItem("username")}!</h1>
    </div>
    <div className=" flex justify-center items-center py-20">
      <h1 className="text-3xl font-bold text-white  flex justify-center">Users: {localStorage.getItem("users")}</h1>
    </div>
    </div>
  );
}
export default Dashboard;