package br.com.zupacademy.natacha.microservicepropostas.carteira;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.commons.enums.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Carteira carteira;


    public CarteiraDigitalRequest(String email, Carteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public CarteiraDigitalRequest(CarteiraDigital carteiraDigital) {
        this.email = email;
        this.carteira = carteira;
    }

    public CarteiraDigital toModel(Cartao cartao) {

        return new CarteiraDigital(this.email, this.carteira, cartao);
    }

    public String getEmail() {
        return email;
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
