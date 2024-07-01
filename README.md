# Projeto de Gestão de Funcionários

## Objetivo

O objetivo deste desafio é avaliar as habilidades de desenvolvimento considerando a organização, boas práticas de codificação, criação de APIs, frameworks, e tecnologias.


## Tecnologias Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Maven

## Configuração e Execução

**Clonar o repositório**

	git clone https://github.com/alexsandromarques/desafio-bento.git

**Configurar o Banco de Dados**

1. Instale e inicie o PostgreSQL
2. Crie um banco de dados vazio com o nome `desafio`
3. Atualize as configurações do banco de dados em `src/main/resources/application.properties`

**Executar a Aplicação**

- Utilize sua IDE preferida ou execute o seguinte comando via terminal na pasta raiz do projeto: mvn spring-boot:run

**Endpoints da API**

- `/funcionario/upload`: POST - Realiza o upload do arquivo CSV com os dados dos funcionários
- `/funcionario/todos`: GET - Retorna todos os dados dos funcionários
- `/funcionario/cpf/{cpf}`: GET - Retorna um funcionário por CPF
   
**Documentação do Swagger**

- `/swagger-ui/index.html`
   
## Autor

Este projeto foi desenvolvido por [Alexsandro Marques](mailto:alexoliveira.marques@gmail.com).

   
   
   


   

