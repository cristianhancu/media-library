import { useState, useEffect } from "react";


type User = {
  id: number;
  username: string;
  createdAt: string;
};

function Home() {
  const [users, setUsers] = useState<User[]>([]);
  

  useEffect(() => {
    fetch("http://localhost:8080/users/all")
      .then((response) => response.json())
      .then((data: User[]) => setUsers(data))
      .catch((error) => console.error("Eroare:", error));
  }, []);

  const sortedUsers = [...users].sort(
    (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
  );

  return (
    <div className="grid grid-flow-col grid-rows-3 gap-4">
      
      <div className="inline-flex bg-white w-xl h-60 rounded-lg justify-center items-center row-span-1 col-span-2">
        <p className="m-auto font-bold">Active users: {users.length}</p>
      </div>

      <div className="inline-flex bg-white w-xl h-60 rounded-lg justify-center items-center col-span-2">
        <p className="m-auto font-bold">Media items created</p>
      </div>

      <div className="inline-flex bg-white w-m h-xl rounded-lg justify-center items-center row-span-2">
        <p className="m-auto font-bold">Users created</p>

      </div>

      <div className="block bg-white w-m h-xl rounded-lg col-span-1 row-span-3">
        <p className="flex font-bold justify-center p-2">Latest users created</p>
        <div className="grid grid-cols-3 font-semibold border-b p-2 text-center">
          <span>No.</span>
          <span>Username</span>
          <span>Created at</span>
        </div>
        <div className="flex flex-col">
          {sortedUsers.map((user) => (
            <div
              key={user.id}
              className="grid grid-cols-3 border-b  text-center">
              <span>{user.id}</span>
              <span>{user.username}</span>
              <span>
                {new Date(user.createdAt).toLocaleString()}
              </span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Home;