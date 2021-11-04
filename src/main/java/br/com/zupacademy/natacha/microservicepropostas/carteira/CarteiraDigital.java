package br.com.zupacademy.natacha.microservicepropostas.carteira;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.commons.enums.Carteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Carteira carteira;

    @NotNull
    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public CarteiraDigital() {
    }


    public CarteiraDigital(@Email @NotBlank String email, @NotNull Carteira carteira, @NotNull Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Carteira getCarteira() {
        return carteira;
    }

}
