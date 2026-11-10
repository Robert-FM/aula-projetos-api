# 🌐 Projetos API

API REST didática da disciplina **Desenvolvimento de Aplicações para Internet**, que demonstra a consulta de um projeto em JSON com Java e Spring Boot. Inclui testes do controller, empacotamento com Maven, imagem Docker e validação por GitHub Actions.

A API foi publicada com **Docker e Coolify em uma VPS Oracle Cloud ARM64 (Ampere A1)**. O GitHub Actions realiza a compilação e os testes; a publicação é iniciada separadamente pelo professor no Coolify.

> 🎓 **Atenção, estudantes:** para a atividade da disciplina, **não é necessário instalar Java, Maven, Docker ou Spring Boot**. O trabalho será feito **pelo navegador**, no GitHub. A execução dos testes ocorre nos servidores do GitHub Actions. O professor demonstra a versão publicada no Coolify.

**Links rápidos:** [Repositório original](https://github.com/Robert-FM/aula-projetos-api) · [API publicada (demonstração)](http://dzk6uwntexlvtb0incigzdci.147.15.87.118.sslip.io/api/projetos/1) · [Documentação Spring REST](https://spring.io/guides/gs/rest-service/)

## 🎯 Atividade prática da disciplina — sem instalar nada

**Situação-problema (PBL):** uma equipe recebeu uma API REST de projetos acadêmicos que já funciona. Sua missão é **adaptar os dados do projeto**, preservar o contrato HTTP/JSON e demonstrar, por meio de testes automatizados, que a alteração não comprometeu a API.

**Requisitos:** computador com navegador e Internet, conta gratuita no GitHub e acesso ao caderno de atividades. A prática será feita em **duplas**.

### Etapas da equipe

1. **Investigar (antes de editar).** Abra a [documentação oficial de serviços REST do Spring](https://spring.io/guides/gs/rest-service/) e identifique o papel de `@RestController`, `@GetMapping` e da resposta JSON. Registre por que `GET /api/projetos/1` é um endpoint de consulta.
2. **Criar um fork.** Acesse [o repositório original](https://github.com/Robert-FM/aula-projetos-api), clique em **Fork** e crie uma cópia na conta de um integrante. Confirme que você está editando o **fork**, não o projeto do professor.
3. **Personalizar o controller.** No fork, abra [`ProjetoController.java`](src/main/java/br/edu/ads/projetosapi/controller/ProjetoController.java), clique no ícone de edição e modifique os **valores** de `nome`, `responsavel` e `status` para representar o projeto escolhido. Preserve o endpoint `/api/projetos/1`, os nomes das propriedades JSON e o campo `id` com valor `1`.
4. **Atualizar o teste.** Abra [`ProjetoControllerTest.java`](src/test/java/br/edu/ads/projetosapi/controller/ProjetoControllerTest.java) e altere os valores esperados em `deveRetornarProjetoEmJson()` para corresponder aos dados escolhidos. Preserve o teste `deveRetornar404ParaCaminhoInexistente()`.
5. **Salvar as alterações.** Faça **Commit changes** no GitHub com uma mensagem explicativa, por exemplo: `Personaliza dados da API e ajusta testes`.
6. **Executar e conferir o CI.** No seu fork, acesse **Actions**, habilite a execução dos workflows se o GitHub solicitar, abra a execução associada ao seu commit e confira os logs do Maven. O resultado esperado é **2 testes executados, 0 falhas e 0 erros**, seguido de `BUILD SUCCESS`.
7. **Investigar uma falha e corrigir.** Para compreender a função dos testes, faça temporariamente **um teste esperar um `nome` diferente do valor retornado pelo controller**, salve e observe a falha no Actions. Depois **corrija o valor esperado**, faça novo commit e confirme novamente os dois testes aprovados. Não altere o contrato da API apenas para forçar o teste a passar.
8. **Entregar.** Envie o **link do fork**, o link ou identificação do **commit final**, uma evidência da execução **bem-sucedida** do GitHub Actions e as respostas solicitadas no Caderno de Atividades. Registre brevemente o que causou a falha proposital e como ela foi solucionada.

> **Importante:** a atividade termina com o código e os testes validados **no fork**. Os estudantes **não publicam seus forks no Coolify** nesta aula e não precisam de acesso administrativo ao servidor. O GitHub Actions testa o código na nuvem, mas **não inicia uma API pública por conta própria**.

### Critérios para verificar o trabalho

- O fork está acessível e contém um commit com as alterações.
- O endpoint e as quatro propriedades JSON (`id`, `nome`, `responsavel`, `status`) foram preservados.
- Os dados personalizados são coerentes com os valores esperados no primeiro teste.
- O segundo teste continua verificando HTTP 404 para `/api/projetos/999`.
- A execução final do GitHub Actions mostra os **dois testes aprovados**.
- A dupla consegue explicar a diferença entre **editar o código**, **validar com CI** e **publicar com Coolify**.

### Demonstração do professor: API publicada

Abra no navegador: **[GET /api/projetos/1 — API da turma](http://dzk6uwntexlvtb0incigzdci.147.15.87.118.sslip.io/api/projetos/1)**.

Essa URL corresponde à versão disponibilizada pelo professor na Oracle Cloud. **Alterações no fork não aparecerão nesse endereço**: para mudar uma aplicação publicada é necessário construir e implantar uma nova versão no ambiente que a hospeda.

---

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

## 📦 Preparação do ambiente — **opcional, para execução local**

**Esta seção não é necessária para realizar a atividade da disciplina.** Ela se destina a quem deseja executar o projeto no próprio computador, fora do fluxo com GitHub Actions.

Após clonar o repositório, entre na pasta do projeto e escolha uma das formas de execução abaixo.

Para execução local, instale **JDK 21** e **Maven**. O Dockerfile utiliza Maven 3.9; o repositório não inclui Maven Wrapper. Confira as ferramentas:

```bash
java -version
mvn -version
```

O Java informado pelo Maven deve corresponder ao JDK 21. A primeira execução precisa de acesso aos repositórios Maven para baixar as dependências.

Para execução em contêiner, instale o **Docker**, com o daemon em execução. Java e Maven são fornecidos pelas imagens do Dockerfile e dispensam instalação local nessa opção.

## ▶️ Execução local — **opcional**

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

O workflow [java-ci.yml](.github/workflows/java-ci.yml) foi utilizado para executar `mvn -B verify` com Java 21 no GitHub Actions. **Consulte a chave `on:` nesse arquivo para confirmar os eventos de disparo configurados na versão atual**. No fork, um commit na branch monitorada deve iniciar a execução, desde que o Actions esteja habilitado e a configuração permita esse evento. O workflow valida o projeto; **não publica a aplicação no Coolify**.

## 🚀 Publicação no Coolify — **demonstração do professor**

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

Ao personalizar os dados, atualize também os valores esperados em `ProjetoControllerTest.java`. O fluxo da atividade é editar o código e os testes no fork, fazer commit e conferir o GitHub Actions. A eventual publicação de uma versão selecionada é uma ação separada, sob responsabilidade do professor no Coolify.

## 👨‍💻 Autor

**Robert Melo**

🔗 LinkedIn: [linkedin.com/in/robertdemelo](https://www.linkedin.com/in/robertdemelo/)

☕ Java | Spring Boot | Maven | Docker | Coolify | Oracle Cloud | APIs REST
