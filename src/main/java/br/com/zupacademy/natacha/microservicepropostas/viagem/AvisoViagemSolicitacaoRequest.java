package br.com.zupacademy.natacha.microservicepropostas.viagem;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemSolicitacaoRequest {

    @NotBlank
    private String destino;

    @NotNull
    @FutureOrPresent
    private LocalDate validoAte;

    public AvisoViagemSolicitacaoRequest(@NotBlank String destino, @NotNull @FutureOrPresent LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
}
