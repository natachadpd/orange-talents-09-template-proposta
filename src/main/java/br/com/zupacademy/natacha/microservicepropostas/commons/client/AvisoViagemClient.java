package br.com.zupacademy.natacha.microservicepropostas.commons.client;

import br.com.zupacademy.natacha.microservicepropostas.viagem.AvisoViagemResponse;
import br.com.zupacademy.natacha.microservicepropostas.viagem.AvisoViagemSolicitacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "avisoViagem", url = "${proposta.sistema-externo.analise-cartao.url}")
public interface AvisoViagemClient {

    @PostMapping("/{numeroCartao}/avisos}")
    AvisoViagemResponse avisoViagem(@PathVariable String numeroCartao,
                                    @RequestBody AvisoViagemSolicitacaoRequest request);

}
