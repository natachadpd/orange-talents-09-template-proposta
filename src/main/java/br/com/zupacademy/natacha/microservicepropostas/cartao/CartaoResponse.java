package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

import java.time.LocalDateTime;

public class CartaoResponse {

    private String numeroCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    private Long idProposta;

    public CartaoResponse(String numeroCartao, LocalDateTime emitidoEm, String titular, Long idProposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public Cartao toModel(NovaProposta proposta)
    {
        return new Cartao(this.numeroCartao, this.emitidoEm, this.titular, proposta);
    }
}
