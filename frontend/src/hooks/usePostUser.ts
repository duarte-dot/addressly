import { createUser } from "@/services/api";
import { useMutation } from "@tanstack/react-query";

export function useCreateUser() {
  return useMutation({
    mutationFn: createUser,
  });
}
