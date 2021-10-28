package br.com.zupacademy.natacha.microservicepropostas.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analise", url = "${proposta.sistema-externo.analise-financeira.url}")
public interface AnaliseFinanceiraClient {

    @PostMapping (consumes = "application/json")
    ResultadoAnaliseResponse enviarAnalise(@RequestBody SolicitacaoAnaliseRequest request);
}
