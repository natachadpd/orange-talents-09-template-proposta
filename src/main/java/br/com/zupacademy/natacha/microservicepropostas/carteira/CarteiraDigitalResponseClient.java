package br.com.zupacademy.natacha.microservicepropostas.carteira;

import br.com.zupacademy.natacha.microservicepropostas.commons.enums.StatusCarteiraDigital;

public class CarteiraDigitalResponseClient {

    private StatusCarteiraDigital resultado;

    private String id;

    public CarteiraDigitalResponseClient() {
    }


    public CarteiraDigitalResponseClient(StatusCarteiraDigital resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusCarteiraDigital getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
