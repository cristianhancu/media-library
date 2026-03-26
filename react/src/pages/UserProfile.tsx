import  { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

type User = {
  id: number;
  username: string;
  email: string;
  createdAt: string;
};

function UserProfile() {
  const { id } = useParams();
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    fetch(`http://localhost:8080/users/${id}`)
      .then((res) => res.json())
      .then((data: User) => setUser(data))
      .catch((err) => console.error(err));
  }, [id]);

  if (!user) return <p>Loading...</p>;

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">User Profile</h1>

      <p><strong>ID:</strong> {user.id}</p>
      <p><strong>Username:</strong> {user.username}</p>
      <p><strong>Email:</strong> {user.email}</p>
      <p><strong>Created:</strong> {new Date(user.createdAt).toLocaleString()}</p>
    </div>
  );
}

export default UserProfile;