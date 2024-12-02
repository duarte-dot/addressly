import { patchUser } from "@/services/api";
import { useMutation } from "@tanstack/react-query";

export function usePatchUser() {
  return useMutation({
    mutationFn: patchUser,
  });
}
