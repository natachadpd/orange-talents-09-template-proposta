package br.com.zupacademy.natacha.microservicepropostas.viagem;

import br.com.zupacademy.natacha.microservicepropostas.commons.enums.StatusAvisoViagem;

public class AvisoViagemResponse {

    private StatusAvisoViagem resultado;

    public AvisoViagemResponse(StatusAvisoViagem resultado) {
        this.resultado = resultado;
    }

    public StatusAvisoViagem getResultado() {
        return resultado;
    }
}
