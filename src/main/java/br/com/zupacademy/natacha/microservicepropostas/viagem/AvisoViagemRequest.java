package br.com.zupacademy.natacha.microservicepropostas.viagem;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @FutureOrPresent
    private LocalDate dataTermino;

    public AvisoViagemRequest(@NotBlank String destino, @NotNull @FutureOrPresent LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public AvisoViagem toModel(String userAgent, String ip, Cartao cartao){
        return new AvisoViagem(cartao, this.destino, this.dataTermino, ip, userAgent);
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
}
