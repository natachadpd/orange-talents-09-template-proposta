package br.com.zupacademy.natacha.microservicepropostas.proposta;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class NovaPropostaControllerTest {



    @Autowired
    private Gson gson;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NovaPropostaRepository repository;

    private final String URI = "/propostas";


    @DisplayName("Retorna erro ao cadastrar proposta com nome nulo ou em branco")
    @ParameterizedTest
    @NullAndEmptySource
    void testeProposta2(String nome) throws Exception {
        NovaPropostaRequest request = new NovaPropostaRequest("596.659.570-40",
                "ada.lovelace@email.com",
                nome,
                "Rua Java , 200 - POO",
                BigDecimal.valueOf(2000));


        String requestJson = gson.toJson(request);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)).andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @DisplayName("Retorna erro ao cadastrar proposta com endereço nulo ou em branco")
    @ParameterizedTest
    @NullAndEmptySource
    void testeProposta3(String endereco) throws Exception {
        NovaPropostaRequest request = new NovaPropostaRequest("596.659.570-40",
                "grace.hopper@email.com",
                "Grace Hopper",
                endereco,
                BigDecimal.valueOf(2000));


        String requestJson = gson.toJson(request);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)).andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @DisplayName("Retorna erro ao cadastrar proposta com documento invalido")
    @ParameterizedTest
    @NullAndEmptySource
    void testeProposta4(String documento) throws Exception {
        NovaPropostaRequest request = new NovaPropostaRequest(documento,
                "grace.hopper@email.com",
                "Grace Hopper",
                "Rua Java, 201 - POO",
                BigDecimal.valueOf(2000));


        String requestJson = gson.toJson(request);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)).andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    @DisplayName("Retorna erro ao tentar cadastrar proposta com documento já existente")
    void testeProposta5() throws Exception {
        NovaPropostaRequest request = new NovaPropostaRequest("596.659.570-40",
                "grace.hopper@email.com",
                "Grace Hopper",
                "Rua Java, 201 - POO",
                BigDecimal.valueOf(2000));

        repository.save(request.toModel());

        NovaPropostaRequest requestDuplicada = new NovaPropostaRequest("596.659.570-40",
                "grace.hopper@email.com",
                "Grace Hopper",
                "Rua Java, 201 - POO",
                BigDecimal.valueOf(2000));

        String requestJson = gson.toJson(request);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(422));


    }

}