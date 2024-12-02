import { User } from "@/services/api";
import { useFetchUsers } from "@/hooks/useFetchUsers";
import { Pencil, Trash } from "lucide-react";
import { Card, CardHeader } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export const DashBoard = () => {
  const { data: users, isLoading, isError } = useFetchUsers();

  if (isLoading) {
    return <p className="text-center mt-10 text-gray-400">Carregando...</p>;
  }

  if (isError) {
    return (
      <p className="text-center mt-10 text-red-500">
        Erro ao carregar os endereços
      </p>
    );
  }

  return (
    <div className="flex flex-col p-6 space-y-8 md:mx-32 text-start">
      <div className="space-y-2 ">
        <h1 className="text-4xl font-bold text-white">
          Bem-vindo(a) ao <span className="text-[#228be6]">Addressly!</span>
        </h1>
        <h2 className="text-lg text-gray-300">
          Simplificando a gestão de endereços
        </h2>
      </div>

      <Button
        className="w-full !mt-10 md:w-auto mx-auto bg-[#1971c2] text-white hover:bg-[#228be6] rounded px-6 py-8 text-lg transition-all"
        onClick={() => {}}
      >
        Cadastrar Novo Endereço
      </Button>

      <div className="w-full space-y-4">
        <h3 className="text-xl font-semibold text-white">
          Esses são seus endereços já cadastrados:
        </h3>

        <div className="flex flex-col gap-4">
          {users?.map((user: User) => (
            <Card
              key={user.id}
              className="shadow-lg hover:shadow-xl transition-all cursor-pointer bg-gray-800 text-white border border-gray-700 rounded hover:border-gray-600 hover:bg-gray-700"
              onClick={() => {}}
            >
              <CardHeader className="flex justify-between items-center flex-row">
                <p className="text-gray-400">
                  {`${user.nome}, ${user.logradouro} (${user.bairro}) - ${user.cep} | ${user.cidade}/${user.estado}`}
                </p>
                <div className="flex gap-3 !mt-0">
                  <Pencil className="cursor-pointer hover:text-green-300 transition-colors" />
                  <Trash className="cursor-pointer hover:text-red-500 transition-colors" />
                </div>
              </CardHeader>
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
};
