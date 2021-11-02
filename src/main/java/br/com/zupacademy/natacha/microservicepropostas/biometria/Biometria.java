package br.com.zupacademy.natacha.microservicepropostas.biometria;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.exceptions.BiometriaException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Cartao cartao;


    @NotBlank
    private String impressaoDigital;

    @Deprecated
    public Biometria() {
    }


    public Biometria(@NotNull Cartao cartao, @NotBlank String impressaoDigital) {
        this.cartao = cartao;
        this.impressaoDigital = impressaoDigital;
    }

    public void base64Biometria(String impressaoDigital) {

        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(impressaoDigital);
        } catch (IllegalArgumentException ex) {
            throw new BiometriaException();
        }
    }

    public Long getId() {
        return id;
    }
}
