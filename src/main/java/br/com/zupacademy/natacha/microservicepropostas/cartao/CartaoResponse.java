package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CartaoResponse {



    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private String idProposta;

    public CartaoResponse(String id, LocalDateTime emitidoEm,
                          String titular, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public Cartao toModel(NovaProposta proposta) {

        return new Cartao(this.id, this.emitidoEm, this.titular, proposta);
    }
}
