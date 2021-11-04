package br.com.zupacademy.natacha.microservicepropostas.commons.client;

import br.com.zupacademy.natacha.microservicepropostas.cartao.SolicitacaoBloqueioRequest;
import br.com.zupacademy.natacha.microservicepropostas.cartao.SolicitacaoBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitacaoBloqueioCartao", url = "${proposta.sistema-externo.analise-cartao.url}")

public interface SolicitacaoBloqueioCartao {

    @PostMapping("/{numeroCartao}/bloqueios")
    SolicitacaoBloqueioResponse bloquearCartao(@PathVariable String numeroCartao,
                                               @RequestBody SolicitacaoBloqueioRequest request);
}
