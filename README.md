# Addressly

Este projeto é um sistema desenvolvido para gerenciar endereços. Para rodar o projeto localmente, você precisará do Docker instalado em sua máquina. A seguir, explico como rodar o projeto.

### Como instalar o Docker

- [Mac](https://docs.docker.com/desktop/setup/install/mac-install/)
- [Windows](https://docs.docker.com/desktop/setup/install/windows-install/)
- [Linux](https://docs.docker.com/desktop/setup/install/linux/)

## Rodando o projeto
### Passo 1: Clonar o repositório

Clone o repositório do **Addressly** em seu diretório local:
```bash
git clone git@github.com:duarte-dot/addressly.git
```

### Passo 2: Entre na pasta do repositório
```bash
cd addressly
```

### Passo 3: Rode o [Docker Compose](https://docs.docker.com/compose/install/)

Dentro da pasta do repositório, execute o comando abaixo para criar e iniciar os containers do Docker:
```bash
docker compose up -d --build
```

- O Frontend estará disponível em:
http://localhost:5173 (abra este em seu navegador :)
- O Backend estará disponível em:
http://localhost:8080

### Comando pra parar o Docker
```bash
docker compose down
```

### Prints do projeto:
![Captura de Tela 2024-12-02 às 19 34 07](https://github.com/user-attachments/assets/8348b899-c2f9-4113-9feb-0c76fc5100d2)
![Captura de Tela 2024-12-02 às 19 34 19](https://github.com/user-attachments/assets/c3fc5b8e-db7c-4f3d-9603-73cc26446ea0)
![Captura de Tela 2024-12-02 às 19 35 06](https://github.com/user-attachments/assets/4caec80e-7db6-4ceb-ac8b-816deabb334a)
![Captura de Tela 2024-12-02 às 19 35 20](https://github.com/user-attachments/assets/d30c4938-3b07-4e2a-ba13-fa0d0e02db8b)
![Captura de Tela 2024-12-02 às 19 35 51](https://github.com/user-attachments/assets/604782dc-391e-4b1f-8d68-26435243434c)
