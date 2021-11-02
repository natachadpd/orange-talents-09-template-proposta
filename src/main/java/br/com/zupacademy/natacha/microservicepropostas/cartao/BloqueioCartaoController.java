package br.com.zupacademy.natacha.microservicepropostas.cartao;

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

    @PostMapping("/numeroCartao")
    @Transactional
    public void bloquearCartao(@RequestParam String numeroCartao,
                               @RequestHeader(value = "User-Agent") String userAgent,
                               HttpServletRequest request) {
        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cartão não encontrado."));

        if (bloqueioRepository.existsByCartaoNumeroCartao(numeroCartao)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já bloqueado");
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(userAgent, request.getRemoteAddr(), cartao);
        bloqueioRepository.save(bloqueioCartao);

    }

}
