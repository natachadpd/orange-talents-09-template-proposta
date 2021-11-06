package br.com.zupacademy.natacha.microservicepropostas.cartao;

import br.com.zupacademy.natacha.microservicepropostas.biometria.BiometriaController;
import br.com.zupacademy.natacha.microservicepropostas.commons.client.SolicitacaoBloqueioCartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/cartoes")
public class BloqueioCartaoController {


    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioCartaoRepository bloqueioRepository;

    @Autowired
    private SolicitacaoBloqueioCartao solicitacaoBloqueio;

    @PostMapping("/numeroCartao/{numeroCartao}/bloqueios")
    @Transactional
    public void bloquearCartao(@PathVariable String numeroCartao,
                               @RequestHeader(value = "User-Agent") String userAgent,
                               HttpServletRequest request) {


        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado."));

        if (bloqueioRepository.existsByCartaoNumeroCartao(numeroCartao)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já bloqueado");
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(userAgent, request.getRemoteAddr(), cartao);
        cartaoBloquear(cartao, bloqueioCartao);

    }


    private void cartaoBloquear(Cartao cartao, BloqueioCartao bloqueioCartao) {

        try {
            solicitacaoBloqueio.bloquearCartao(cartao.getNumeroCartao(),
                    new SolicitacaoBloqueioRequest("proposta"));
            cartao.bloquear();
            cartaoRepository.save(cartao);
            bloqueioRepository.save(bloqueioCartao);
        } catch (FeignException.UnprocessableEntity feignException) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (FeignException ex) {
            System.out.println(ex.getMessage());
            ex.getMessage();
        }
    }

}
