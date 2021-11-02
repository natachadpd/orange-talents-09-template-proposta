package br.com.zupacademy.natacha.microservicepropostas.biometria;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    @JsonProperty
    private String impressaoDigital;

    public BiometriaRequest() {
    }

    public BiometriaRequest(@NotBlank String impressaoDigital) {
        this.impressaoDigital = impressaoDigital;
    }

    public Biometria toModel(Cartao cartao) {

        return new Biometria(cartao, this.impressaoDigital);
    }

    public String getImpressaoDigital() {
        return impressaoDigital;
    }
}


