package br.com.zupacademy.natacha.microservicepropostas.cartao;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataBloqueio = LocalDateTime.now();

    @NotBlank
    private String userAgent;

    @NotBlank
    private String ipCliente;

    @NotNull
    @OneToOne
    private Cartao cartao;


    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(@NotBlank String userAgent, @NotBlank String ipCliente,
                          @NotNull Cartao cartao) {
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
        this.cartao = cartao;
    }

}
