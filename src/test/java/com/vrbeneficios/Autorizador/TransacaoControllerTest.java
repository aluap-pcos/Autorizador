package com.vrbeneficios.Autorizador;

import com.vrbeneficios.Autorizador.controller.form.TransacaoForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRealixarUmaTransacao() throws Exception {
        criarCartao();

        URI uri = new URI("/transacao");
        String body = "{\"numeroCartao\" : \"1234567890098765\", \"senhaCartao\" : \"1234\", \"valor\" : 10.00}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaDevolver422QuandoOSaldoEInsuficiente() throws Exception {
        criarCartao();

        URI uri = new URI("/transacao");
        String body = "{\"numeroCartao\" : \"1234567890098765\", \"senhaCartao\" : \"1234\", \"valor\" : 600.00}";

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
    public void deveriaDevolver422QuandoOCartaoNaoExiste() throws Exception {
        URI uri = new URI("/transacao");
        String body = "{\"numeroCartao\" : \"1234567890098769\", \"senhaCartao\" : \"1234\", \"valor\" : 10.00}";

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
    public void deveriaDevolver422QuandoASenhaEstaIncorreta() throws Exception {
        criarCartao();

        URI uri = new URI("/transacao");
        String body = "{\"numeroCartao\" : \"1234567890098765\", \"senhaCartao\" : \"1230\", \"valor\" : 10.00}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(422));
    }

    public void criarCartao() throws Exception {
        URI uri = new URI("/cartoes");
        String body = "{\"numeroCartao\": \"1234567890098765\", \"senha\": \"1234\"}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
