import React, { useState, useEffect } from "react";


  type User = {
  id: number;
  username: string;
  email: string;
  createdAt: string;
};

function Users() {
  
  const [users, setUsers] = useState<User[]>([]);
  const [searchInput, setSearchInput] = useState("");
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/users/all")
      .then((response) => response.json())
      .then((data: User[]) => setUsers(data))
      .catch((error) => console.error("Eroare:", error));
  }, []);

  const sortedUsers = [...users].sort(
    (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
  );
    const filteredUsers = sortedUsers.filter((user) =>
      user.username.toLowerCase().includes(searchTerm.toLowerCase())||user.email.toLowerCase().includes(searchTerm.toLowerCase())
    );
    
    return(

      <div className="grid grid-flow-col grid-rows-3 gap-4 ">
    <div className="block bg-white w-m h- rounded-lg col-span-1 row-span-3">
        <p className="flex font-bold justify-center p-2">Latest users created</p>
        <div className="grid grid-cols-3 font-semibold border-b p-2 text-center">
          <span>No.</span>
          <span>Username</span>
          <span>Created at</span>
        </div>
        <div className="flex flex-col">
          {filteredUsers.map((user) => (
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
      <div className="bg-white">
        <p className="flex font-bold justify-center p-2">SEARCH</p>
           <input
          type="text"
          value={searchInput}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setSearchInput(e.target.value)
          }
          onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => {
            if (e.key === "Enter") {
              setSearchTerm(searchInput);
            }
          }}
          placeholder="Find by username"
          className="border p-2 rounded w-full mb-2"
        />
        <button
        onClick={() => setSearchTerm(searchInput)}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold px-4 rounded-full"
        >
          Search
        </button>
      </div>
      </div>
  );
}

export default Users;