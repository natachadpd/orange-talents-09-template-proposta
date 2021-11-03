package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.commons.enums.StatusCartao;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoBloqueioResponse {

    @JsonProperty
    private StatusCartao resultado;


    public SolicitacaoBloqueioResponse(StatusCartao resultado) {
        this.resultado = resultado;
    }

    @Deprecated
    public SolicitacaoBloqueioResponse() {
    }




}

