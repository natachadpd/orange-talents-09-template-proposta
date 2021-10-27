package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private String titular;

    private LocalDateTime emitidoEm = LocalDateTime.now();

    @OneToOne
    private NovaProposta proposta;

    @Deprecated
    public Cartao() {
    }


    public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, NovaProposta proposta) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;
    }


}
