# 🌐 Projetos API

API REST didática da disciplina **Desenvolvimento de Aplicações para Internet**, que demonstra a consulta de um projeto em JSON com Java e Spring Boot. Inclui testes do controller, empacotamento com Maven, imagem Docker e validação por GitHub Actions.

A API foi publicada com **Docker e Coolify em uma VPS Oracle Cloud ARM64 (Ampere A1)**. O GitHub Actions realiza a compilação e os testes; a publicação é iniciada separadamente pelo professor no Coolify.

## ✨ Funcionalidades

- Consulta de um projeto fixo pelo endpoint `GET /api/projetos/1`.
- Resposta JSON com identificador, nome, responsável e status.
- Configuração da porta por variável de ambiente.
- Execução local ou em contêiner e publicação no Coolify.

Os dados são definidos no código, sem banco de dados ou serviços externos. O caminho `/api/projetos/1` é literal: não há consulta por identificador variável, listagem, cadastro, atualização ou exclusão de projetos.

## 🛠️ Tecnologias

| Tecnologia | Uso no projeto |
| --- | --- |
| Java 21 | Linguagem e versão configurada no Maven, Docker e CI |
| Spring Boot 4.0.9 | Inicialização e configuração da aplicação |
| Spring Web MVC | Endpoint HTTP, pelo `spring-boot-starter-webmvc` |
| Maven | Dependências, testes e empacotamento |
| JUnit Jupiter e MockMvc | Testes, pelo `spring-boot-starter-webmvc-test` |
| Docker | Build em múltiplos estágios e execução do JAR |
| GitHub Actions | Compilação e testes automatizados |
| Coolify | Gerenciamento do build, deploy, contêiner e domínio |
| Oracle Cloud | Hospedagem em VPS ARM64 (Ampere A1) |

As dependências e o plugin de empacotamento estão definidos no [pom.xml](pom.xml).

## 📁 Organização do projeto

```text
projetos-api/
├── .github/workflows/java-ci.yml
├── src/
│   ├── main/
│   │   ├── java/br/edu/ads/projetosapi/
│   │   │   ├── ProjetosApiApplication.java
│   │   │   └── controller/ProjetoController.java
│   │   └── resources/application.properties
│   └── test/java/br/edu/ads/projetosapi/controller/
│       └── ProjetoControllerTest.java
├── .dockerignore
├── Dockerfile
├── pom.xml
└── README.md
```

`ProjetosApiApplication` é o ponto de entrada. O Spring MVC encaminha a requisição para `ProjetoController`, que retorna um `Map` com os dados fixos, serializado em JSON. `application.properties` define o nome da aplicação, o endereço de escuta e a porta.

## 📦 Preparação do ambiente

Após clonar o repositório, entre na pasta `projetos-api` e escolha uma das formas de execução abaixo.

Para execução local, instale **JDK 21** e **Maven**. O Dockerfile utiliza Maven 3.9; o repositório não inclui Maven Wrapper. Confira as ferramentas:

```bash
java -version
mvn -version
```

O Java informado pelo Maven deve corresponder ao JDK 21. A primeira execução precisa de acesso aos repositórios Maven para baixar as dependências.

Para execução em contêiner, instale o **Docker**, com o daemon em execução. Java e Maven são fornecidos pelas imagens do Dockerfile e dispensam instalação local nessa opção.

## ▶️ Execução

### Com Maven

Na raiz do repositório:

```bash
mvn spring-boot:run
```

O Maven resolve as dependências e inicia a aplicação. Por padrão, o endpoint fica disponível em `http://localhost:8080/api/projetos/1`.

Para gerar e executar o JAR:

```bash
mvn clean package
java -jar target/projetos-api-0.0.1-SNAPSHOT.jar
```

O empacotamento também executa os testes. Encerre a aplicação com `Ctrl+C` antes de iniciar outra instância na mesma porta.

### Com Docker

Na raiz do repositório:

```bash
docker build -t projetos-api .
docker run --rm -p 8080:8080 projetos-api
```

O build utiliza `maven:3.9-eclipse-temurin-21` para gerar o JAR e `eclipse-temurin:21-jre` para executá-lo. O endpoint fica acessível no mesmo endereço da execução local.

**O Dockerfile ignora a execução dos testes** ao empacotar (`-DskipTests`). Utilize o comando da seção de testes ou o workflow de CI para validá-los.

## ⚙️ Configuração

| Variável | Obrigatória | Finalidade | Padrão |
| --- | --- | --- | --- |
| `PORT` | Não | Porta HTTP da aplicação | `8080` |

A aplicação escuta em `0.0.0.0`. Não é necessário criar arquivo `.env` para o fluxo padrão.

Para iniciar em outra porta, por exemplo `8081`:

**PowerShell:**

```powershell
$env:PORT = "8081"
mvn spring-boot:run
```

**Bash:**

```bash
PORT=8081 mvn spring-boot:run
```

**Docker:**

```bash
docker run --rm -e PORT=8081 -p 8081:8081 projetos-api
```

Nesses exemplos, acesse `http://localhost:8081/api/projetos/1`. Em contêiner, a porta interna do mapeamento deve acompanhar o valor de `PORT`.

## 💻 Consulta à API

Com a aplicação em execução, abra `http://localhost:8080/api/projetos/1` no navegador ou utilize um cliente HTTP.

**Bash com curl:**

```bash
curl -i http://localhost:8080/api/projetos/1
```

**PowerShell:**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/projetos/1" -Method Get
```

Resposta esperada: **HTTP 200**, conteúdo `application/json` e corpo abaixo. A ordem das propriedades pode variar.

```json
{
  "id": 1,
  "nome": "Sistema de Gestao Academica",
  "responsavel": "Equipe ADS",
  "status": "Em desenvolvimento"
}
```

O caminho `/api/projetos/999` retorna **HTTP 404**, pois não existe um endpoint mapeado para ele. A raiz `/` também não possui página ou endpoint definido.

## ✅ Testes e integração contínua

Execute a validação com Maven:

```bash
mvn -B verify
```

Os dois testes de [ProjetoControllerTest.java](src/test/java/br/edu/ads/projetosapi/controller/ProjetoControllerTest.java) utilizam `@WebMvcTest` e MockMvc para verificar:

- HTTP 200, tipo de conteúdo JSON e os quatro campos do projeto em `/api/projetos/1`.
- HTTP 404 ao consultar `/api/projetos/999`.

O workflow [java-ci.yml](.github/workflows/java-ci.yml) executa `mvn -B verify` em Ubuntu com Temurin 21 a cada push, pull request ou acionamento manual. Ele valida o projeto; não publica a aplicação no Coolify.

## 🚀 Publicação no Coolify

A publicação utilizada na disciplina é feita em uma VPS **Oracle Cloud ARM64 (Ampere A1)**. O Coolify obtém o código do GitHub, constrói a imagem a partir do Dockerfile e executa a aplicação em um contêiner Docker. Seu proxy reverso encaminha as requisições do domínio público para a porta interna `8080`, sem necessidade de expor essa porta diretamente à Internet.

O deploy é iniciado pelo professor no Coolify. Um build aprovado no GitHub Actions não publica a aplicação automaticamente. Da mesma forma, alterações em forks dos estudantes não modificam a API pública do professor.

Para reproduzir a configuração em uma instância do Coolify:

1. Crie um recurso a partir de **Public Repository** ou da integração **GitHub App**.
2. Selecione o repositório e a branch que deseja publicar.
3. Escolha **Dockerfile** como Build Pack, com a raiz do repositório como diretório base (`/`) e o arquivo `/Dockerfile`.
4. Configure a porta exposta como `8080`, mantendo o padrão da aplicação. Se definir `PORT`, ajuste também a porta configurada na plataforma.
5. Configure o domínio e faça o deploy.
6. Acesse `/api/projetos/1` no domínio publicado, usando HTTP ou HTTPS conforme a configuração do serviço.

Esse fluxo realiza a compilação no servidor e dispensa a instalação de Java, Maven ou Docker na máquina do estudante. As imagens base precisam estar disponíveis para a arquitetura `linux/arm64` da VPS.

### Healthcheck

O recurso no Coolify foi configurado para consultar o próprio endpoint da aplicação:

| Configuração | Valor |
| --- | --- |
| Método e protocolo | `GET`, HTTP |
| Host e porta | `localhost`, `8080` |
| Caminho | `/api/projetos/1` |
| Status esperado | `200` |
| Texto de resposta esperado | Vazio |
| Intervalo | 30 segundos |
| Timeout | 5 segundos |
| Tentativas | 3 |
| Período inicial | 60 segundos |

Essa configuração pertence ao recurso no Coolify; não há instrução `HEALTHCHECK` no Dockerfile. A verificação acompanha a disponibilidade do endpoint e não substitui os testes automatizados.

Os dados de exemplo são definidos em [ProjetoController.java](src/main/java/br/edu/ads/projetosapi/controller/ProjetoController.java). Alterações nesse arquivo exigem uma nova compilação e um novo deploy para aparecerem na aplicação publicada.

Ao personalizar os dados, atualize também os valores esperados em `ProjetoControllerTest.java`. O fluxo da atividade é editar o código e os testes no fork, fazer commit e conferir o GitHub Actions. O professor realiza a publicação das versões selecionadas no Coolify.

## 👨‍💻 Autor

**Robert Melo**

🔗 LinkedIn: [linkedin.com/in/robertdemelo](https://www.linkedin.com/in/robertdemelo/)

☕ Java | Spring Boot | Maven | Docker | Coolify | Oracle Cloud | APIs REST
