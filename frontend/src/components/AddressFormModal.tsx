import { useFetchAddress } from "@/hooks/useFetchAddress";
import { useCreateUser } from "@/hooks/usePostUser";
import { AxiosError } from "axios";
import React, { useEffect } from "react";
import toast from "react-hot-toast";
import { Button } from "./ui/button";
import * as z from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Form, FormControl, FormField, FormItem, FormMessage } from "./ui/form";
import { Input } from "./ui/input";

const formSchema = z.object({
  nome: z.string().min(3, { message: "Nome deve ter no mínimo 3 caracteres" }),
  cpf: z.string().length(11, { message: "CPF deve ter 11 números" }),
  cep: z.string().length(8, { message: "CEP deve ter 8 números" }),
  logradouro: z
    .string()
    .min(3, { message: "Logradouro deve ter no mínimo 3 caracteres" }),
  bairro: z
    .string()
    .min(3, { message: "Bairro deve ter no mínimo 3 caracteres" }),
  cidade: z
    .string()
    .min(3, { message: "Cidade deve ter no mínimo 3 caracteres" }),
  estado: z
    .string()
    .min(2, { message: "Estado deve ter no mínimo 2 caracteres" }),
});

interface AddressFormModalProps {
  setIsModalOpen: React.Dispatch<React.SetStateAction<boolean>>;
  handleUserAdded: () => void;
}

export const AddressFormModal: React.FC<AddressFormModalProps> = ({
  setIsModalOpen,
  handleUserAdded,
}) => {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      nome: "",
      cpf: "",
      cep: "",
      logradouro: "",
      bairro: "",
      cidade: "",
      estado: "",
    },
  });

  const { mutate, isSuccess, error } = useCreateUser();
  const { data: address, isFetching } = useFetchAddress(form.watch("cep"));

  const handleSubmit = async (data: z.infer<typeof formSchema>) => {
    mutate(data);
  };

  useEffect(() => {
    if (address) {
      form.setValue("logradouro", address.logradouro || "");
      form.setValue("bairro", address.bairro || "");
      form.setValue("cidade", address.cidade || "");
      form.setValue("estado", address.uf || "");

      form.trigger(["logradouro", "bairro", "cidade", "estado"]);
    }
  }, [address, form]);

  useEffect(() => {
    if (isSuccess) {
      handleUserAdded();
    }
  }, [isSuccess, handleUserAdded]);

  useEffect(() => {
    if (error) {
      const axiosError = error as AxiosError<{ erro: string }>;
      toast.error(axiosError.response?.data.erro || "Erro desconhecido");
    }
  }, [error]);

  const getPlaceholder = (fieldName: string) => {
    if (fieldName === "cpf" || fieldName === "cep") {
      return fieldName.toUpperCase();
    }
    return fieldName.charAt(0).toUpperCase() + fieldName.slice(1);
  };

  return (
    <div className="!mt-0 fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center p-4">
      <div className="bg-gray-900 p-6 rounded  max-w-lg shadow-lg">
        <h2 className="text-2xl font-semibold text-white mb-6 text-center">
          Cadastrar Novo Endereço
        </h2>
        <Form {...form}>
          <form
            onSubmit={form.handleSubmit(handleSubmit)}
            className="space-y-4"
          >
            {[
              "nome",
              "cpf",
              "cep",
              "logradouro",
              "bairro",
              "cidade",
              "estado",
            ].map((field) => (
              <FormField
                key={field}
                control={form.control}
                name={field as keyof z.infer<typeof formSchema>}
                render={({ field }) => (
                  <FormItem className="text-white">
                    <FormControl>
                      <Input
                        {...field}
                        placeholder={getPlaceholder(field.name)}
                        className="bg-gray-800 text-white placeholder-gray-500 border border-gray-700 focus:ring-2 focus:ring-indigo-500 focus:outline-none rounded- px-4 py-2"
                      />
                    </FormControl>
                    <FormMessage className="text-[0.5rem] text-start !mt-0 text-red-400" />
                  </FormItem>
                )}
              />
            ))}
            <div className="flex w-full !mt-8 justify-between items-center h-full">
              <Button
                onClick={() => setIsModalOpen(false)}
                className="rounded text-white font-semibold bg-red-600 hover:bg-red-700"
              >
                Fechar
              </Button>

              <Button
                type="submit"
                disabled={isFetching}
                className={`rounded text-white font-semibold ${
                  isFetching
                    ? "bg-indigo-500 cursor-not-allowed"
                    : "bg-indigo-600 hover:bg-indigo-700"
                }`}
              >
                {isFetching ? "Carregando..." : "Cadastrar"}
              </Button>
            </div>
          </form>
        </Form>
      </div>
    </div>
  );
};
