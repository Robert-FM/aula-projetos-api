# Projetos API — Spring Boot e Coolify

Exemplo didatico para a disciplina Desenvolvimento de Aplicacoes para Internet.

## Endpoint

`GET /api/projetos/1`

Resposta esperada (ordem das propriedades pode variar):

```json
{"id":1,"nome":"Sistema de Gestao Academica","responsavel":"Equipe ADS","status":"Em desenvolvimento"}
```

## Publicar sem instalar ferramentas locais

1. Crie um repositorio GitHub chamado `projetos-api` usando o navegador.
2. Envie todos os arquivos da pasta deste projeto, preservando `src/main/...`.
3. No Coolify, abra um projeto e use `+ New > Public Repository` (ou GitHub App).
4. Informe o repositorio, a branch principal e escolha **Dockerfile** como Build Pack.
5. Base Directory: `/`; Dockerfile Location: `/Dockerfile` (ajuste conforme a interface).
6. Ports Exposes: `8080`.
7. Configure um dominio gerado pelo Coolify, se disponivel, e clique em Deploy.
8. Acesse `https://SEU-ENDERECO/api/projetos/1` (ou http conforme configurado).

## Como modificar

Edite `src/main/java/br/edu/ads/projetosapi/controller/ProjetoController.java` pelo editor Web do GitHub, faca commit e redeploy no Coolify.

## Observacoes

- Java e Maven estao apenas nas imagens Docker de build/execucao da VPS.
- Esta API usa dados fixos, sem banco de dados.
- Nao exponha segredos no repositorio.
- A imagem base precisa suportar ARM64/aarch64 na VPS Oracle Ampere A1.
