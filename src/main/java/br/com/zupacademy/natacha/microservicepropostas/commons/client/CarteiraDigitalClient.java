package br.com.zupacademy.natacha.microservicepropostas.commons.client;

import br.com.zupacademy.natacha.microservicepropostas.carteira.CarteiraDigitalRequest;
import br.com.zupacademy.natacha.microservicepropostas.carteira.CarteiraDigitalResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteiraDigital", url = "${proposta.sistema-externo.analise-cartao.url}")
public interface CarteiraDigitalClient {

    @PostMapping("/{numeroCartao}/carteiras")
    CarteiraDigitalResponseClient associarCarteira(@PathVariable String numeroCartao,
                                                   @RequestBody CarteiraDigitalRequest request);
}
