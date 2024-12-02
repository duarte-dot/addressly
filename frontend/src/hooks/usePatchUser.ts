import { patchUser } from "@/services/api";
import { useMutation, useQueryClient } from "@tanstack/react-query";

export function usePatchUser() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: patchUser,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });
}
