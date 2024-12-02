import { useQuery } from "@tanstack/react-query";
import { fetchUsers, User } from "../services/api";

export const useFetchUsers = () => {
  return useQuery<User[]>({
    queryKey: ["users"],
    queryFn: () => fetchUsers(),
  });
};
