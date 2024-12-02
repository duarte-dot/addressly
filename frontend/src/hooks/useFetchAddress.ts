import { useQuery } from "@tanstack/react-query";
import { fetchAddress, Address } from "../services/api";

export const useFetchAddress = (cep: string) => {
  return useQuery<Address>({
    queryKey: ["address", cep],
    queryFn: () => fetchAddress(cep),
    enabled: cep.length === 8,
  });
};
