package com.vrbeneficios.Autorizador;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaDevolver422AoTentarCriarUmCartaoQueJaExiste() throws Exception {
        URI uri = new URI("/cartoes");
        String body = "{\"numeroCartao\": \"1234567890098765\", \"senha\": \"1234\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(422));
    }

    @Test
    public void deveriaDevolver404AoTentarConsultarOSaldoDeUmCartaoQueNaoExiste() throws Exception {
        URI uri = new URI("/cartoes/1231231231231231");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

}
