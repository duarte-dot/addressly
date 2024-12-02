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
