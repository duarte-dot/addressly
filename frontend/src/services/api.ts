import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080",
});

export interface User {
  id?: number;
  nome: string;
  cpf: string;
  cep: string;
  logradouro: string;
  bairro: string;
  cidade: string;
  estado: string;
}

export interface Address {
  cep: string;
  logradouro: string;
  bairro: string;
  cidade: string;
  estado: string;
  uf: string;
}

// Função para criar um usuário
export const createUser = async (user: User) => {
  return await api.post("/users", user);
};

// Função para atualizar parcialmente um usuário
export const patchUser = async (user: Partial<User>) => {
  return await api.patch(`/users/${user.id}`, user);
};

// Função para deletar um usuário
export const deleteUser = async (user: User) => {
  return await api.delete(`/users/${user.id}`);
};

// Função para buscar todos os usuários
export const fetchUsers = async (): Promise<User[]> => {
  const response = await api.get("/users");
  return response.data;
};

// Função para buscar endereço por CEP
export const fetchAddress = async (cep: string): Promise<Address> => {
  const response = await api.get(`/addresses/${cep}`);
  return response.data;
};
