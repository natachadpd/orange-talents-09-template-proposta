package br.com.zupacademy.natacha.microservicepropostas.viagem;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String destino;

    @NotNull
    private LocalDateTime dataAviso = LocalDateTime.now();

    @NotNull
    @FutureOrPresent
    private LocalDate dataTermino;

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;



    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(@NotNull Cartao cartao, @NotBlank String destino, @NotNull @FutureOrPresent LocalDate dataTermino,
                       @NotBlank String ipCliente, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
}
