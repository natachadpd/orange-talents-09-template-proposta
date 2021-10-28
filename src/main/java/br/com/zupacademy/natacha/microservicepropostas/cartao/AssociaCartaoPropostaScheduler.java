package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.cartao.client.CartaoClient;
import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;
import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaPropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaCartaoPropostaScheduler {

    @Autowired
    private NovaPropostaRepository repository;

    @Autowired
    private CartaoClient client;


    @Scheduled(fixedRateString = "5000")
    public void associaCartao() {

        List<NovaProposta> propostas = repository.findBySatusAndCartaoId();

        for (NovaProposta proposta : propostas) {

            try {

                CartaoResponse cartaoResponse = client.consultaCartao(proposta.getId().toString());
                Cartao cartao = cartaoResponse.toModel(proposta);

                proposta.associaCartao(cartao);
                repository.save(proposta);

            } catch (FeignException ex) {
                System.out.println(ex.getMessage());
                ex.getMessage();
            }


        }
    }
}
