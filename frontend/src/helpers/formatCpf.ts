export const formatCPF = (value: string): string => {
  const cpf = value.replace(/\D/g, "");

  return cpf
    .replace(/(\d{3})(\d)/, "$1.$2")
    .replace(/(\d{3})(\d)/, "$1.$2")
    .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
};

export const normalizeCPF = (value: string): string => {
  return value.replace(/\D/g, "");
};
