package br.com.zupacademy.natacha.microservicepropostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SolicitacaoBloqueioRequest {

    @JsonProperty
    @NotBlank
    private String sistemaResponsavel;

    public SolicitacaoBloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
