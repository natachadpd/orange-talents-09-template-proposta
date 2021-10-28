package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    private String numeroCartao;

    @NotBlank
    private String titular;

    @NotNull
    private LocalDateTime emitidoEm = LocalDateTime.now();

    @NotNull
    @OneToOne(mappedBy = "cartao")
    private NovaProposta proposta;

    @Deprecated
    public Cartao() {
    }


    public Cartao(@NotBlank String numeroCartao, @NotNull LocalDateTime emitidoEm,
                  @NotBlank String titular, @NotNull NovaProposta proposta) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;
    }


    public String getNumeroCartao() {
        return numeroCartao;
    }
}
