import React, { useState } from "react";
import { X, User, Building2, Pencil, Trash } from "lucide-react";
import { usePatchUser } from "@/hooks/usePatchUser";
import { useDeleteUser } from "@/hooks/useDeleteUser";
import toast from "react-hot-toast";
import type { AxiosError } from "axios";
import { formatCPF, normalizeCPF } from "@/helpers/formatCpf";

interface UserDetailsProps {
  user: {
    nome: string;
    cpf: string;
    logradouro: string;
    cep: string;
    cidade: string;
    estado: string;
    bairro: string;
  };
  onClose: () => void;
}

const EditableField: React.FC<{
  field: keyof UserDetailsProps["user"];
  value: string;
  isEditing: boolean;
  onEdit: () => void;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}> = ({ field, value, isEditing, onEdit, onChange }) => {
  return (
    <p className="text-sm">
      {isEditing ? (
        <input
          type="text"
          name={field}
          value={value}
          onChange={onChange}
          className="bg-gray-700 text-white p-2 rounded"
        />
      ) : (
        <>
          {value}
          <button
            className="ml-2 text-gray-400 hover:text-green-300"
            onClick={onEdit}
          >
            <Pencil size={16} />
          </button>
        </>
      )}
    </p>
  );
};

export const UserDetails: React.FC<UserDetailsProps> = ({ user, onClose }) => {
  const { mutateAsync: updateUser } = usePatchUser();
  const { mutateAsync: deleteUser } = useDeleteUser();

  const [editedUser, setEditedUser] = useState(user);
  const [isEditing, setIsEditing] = useState<
    keyof UserDetailsProps["user"] | null
  >(null);

  const handleEdit = (field: keyof UserDetailsProps["user"]) => {
    setIsEditing(field);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    const formattedValue = name === "cpf" ? formatCPF(value) : value;

    setEditedUser({ ...editedUser, [name]: formattedValue });
  };

  const handleSave = async () => {
    try {
      const cleanUser = {
        ...editedUser,
        cpf: normalizeCPF(editedUser.cpf),
      };
      await updateUser(cleanUser);
      setIsEditing(null);
      toast.success("Usuário atualizado com sucesso!");
    } catch (err: unknown) {
      handleError(err);
    }
  };

  const handleDelete = async () => {
    try {
      await deleteUser(user);
      onClose();
      toast.success("Usuário deletado com sucesso!");
    } catch (err: unknown) {
      handleError(err);
    }
  };

  const handleError = (err: unknown) => {
    if ((err as AxiosError<{ erro: string }>).isAxiosError) {
      const axiosError = err as AxiosError<{ erro: string }>;
      toast.error(axiosError.response?.data.erro || "Erro desconhecido");
    }
  };

  return (
    <div className="!mt-0 fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-gray-800 text-white shadow-lg w-[90%] max-w-md p-6 relative rounded">
        <button
          className="absolute top-3 right-3 text-gray-400 transition-colors flex gap-2"
          onClick={onClose}
        >
          <Trash
            size={24}
            className="hover:text-red-500"
            onClick={handleDelete}
          />
          <X size={24} className="hover:text-gray-900" />
        </button>
        <div className="flex items-start mb-4">
          <User size={48} className="text-white" />
          <div className="ml-4">
            <h2 className="text-2xl font-semibold">
              <EditableField
                field="nome"
                value={editedUser.nome}
                isEditing={isEditing === "nome"}
                onEdit={() => handleEdit("nome")}
                onChange={handleChange}
              />
            </h2>
            <EditableField
              field="cpf"
              value={formatCPF(editedUser.cpf)}
              isEditing={isEditing === "cpf"}
              onEdit={() => handleEdit("cpf")}
              onChange={handleChange}
            />
          </div>
        </div>
        <hr className="my-4 border-gray-600" />
        <div className="flex items-start">
          <Building2 size={48} className="text-white" />
          <div className="ml-4">
            <EditableField
              field="logradouro"
              value={editedUser.logradouro}
              isEditing={isEditing === "logradouro"}
              onEdit={() => handleEdit("logradouro")}
              onChange={handleChange}
            />
            <EditableField
              field="cep"
              value={editedUser.cep}
              isEditing={isEditing === "cep"}
              onEdit={() => handleEdit("cep")}
              onChange={handleChange}
            />
            <EditableField
              field="cidade"
              value={editedUser.cidade}
              isEditing={isEditing === "cidade"}
              onEdit={() => handleEdit("cidade")}
              onChange={handleChange}
            />
            <EditableField
              field="estado"
              value={editedUser.estado}
              isEditing={isEditing === "estado"}
              onEdit={() => handleEdit("estado")}
              onChange={handleChange}
            />
            <EditableField
              field="bairro"
              value={editedUser.bairro}
              isEditing={isEditing === "bairro"}
              onEdit={() => handleEdit("bairro")}
              onChange={handleChange}
            />
          </div>
        </div>
        {isEditing && (
          <button
            onClick={handleSave}
            className="mt-4 bg-blue-600 text-white px-4 py-2 rounded"
          >
            Salvar modificações
          </button>
        )}
      </div>
    </div>
  );
};
