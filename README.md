# Bazar

Projeto desenvolvido para a disciplina de Web 2 do curso de TADS no IFPE.

## Principais Features
- Cadastro, edição, listagem e remoção de Lotes
- Gerenciamento de Órgãos Donatários e Fiscalizadores
- Interface web com páginas para cada entidade (CRUD)
- Utilização de DTOs para transferência de dados
- Persistência de dados com repositórios customizados
- Estrutura MVC com controllers, models e views (Thymeleaf)
- Scripts SQL para criação do banco de dados

## Tecnologias
- Java
- Spring Boot (estrutura MVC)
- Thymeleaf
- JDBC
- HTML, CSS, JavaScript

---
Projeto acadêmico - IFPE TADS Web 2

## Recomendação

Recomendo usar o docker compose que está no projeto para subir o banco. Se preferir usar
o banco da sua máquina local, o SQL para criação das tabelas está presente em 
`src/main/resources/schema.sql`
