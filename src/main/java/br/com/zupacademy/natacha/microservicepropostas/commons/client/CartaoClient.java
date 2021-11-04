package br.com.zupacademy.natacha.microservicepropostas.commons.client;

import br.com.zupacademy.natacha.microservicepropostas.cartao.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${proposta.sistema-externo.analise-cartao.url}" )
public interface CartaoClient {

    @GetMapping(consumes = "application/json")
    CartaoResponse consultaCartao(@RequestParam String idProposta);

}