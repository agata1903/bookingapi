# Booking API

API REST desenvolvida com Spring Boot para gerenciamento de usuários, autenticação e agendamentos.

## Sobre o projeto
Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em contrução de APIs REST, utilizando boas práticas de arquitetura e organização de código.
A aplicação será responsável por gerenciar usuários e permitir o agendamento de serviços, com controle de disponibilidade e autenticação segura.

## Tecnologias utilizadas
- Java 23
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Hibernate

## Estrutura do projeto
- Controller -> Recebe e trata requisições HTTP
- Service -> Contém as regras de negócio
- Repository -> Responsável pela comunicação com banco de dados
- Model -> Representação das entidades
- DTO -> Transferência de dados entre camadas
- Exception -> Tratamento de erros da aplicação

## Funcionalidades
### Usuários e autenticação
- Cadastro de usuários
- Login de usuários
- Controle de tentativas de acesso
- Bloqueio de contas

### Agendamentos
- Criação de agendamentos
- Listagem de agendamentos
- Atualização de horários
- Cancelamento de agendamentos

## Aprendizados
- Estruturação de APIs REST com Spring Boot
- Tratamento de erros HTTP com _ResponseStatusException_
- Aplicação de arquitetura em camadas
- Integração com banco de dados relacional
- Boas práticas de organização de código