package br.edu.ads.projetosapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarProjetoEmJson() throws Exception {
        mockMvc.perform(get("/api/projetos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Sistema de Gestao Academica"))
                .andExpect(jsonPath("$.responsavel").value("Equipe ADS"))
                .andExpect(jsonPath("$.status").value("Em desenvolvimento"));
    }

    @Test
    void deveRetornar404ParaCaminhoInexistente() throws Exception {
        mockMvc.perform(get("/api/projetos/999"))
                .andExpect(status().isNotFound());
    }
}
