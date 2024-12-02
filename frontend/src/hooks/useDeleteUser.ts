import { deleteUser } from "@/services/api";
import { useMutation } from "@tanstack/react-query";

export function useDeleteUser() {
  return useMutation({
    mutationFn: deleteUser,
  });
}
