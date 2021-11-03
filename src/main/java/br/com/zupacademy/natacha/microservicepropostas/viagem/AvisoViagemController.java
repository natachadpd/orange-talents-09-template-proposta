package br.com.zupacademy.natacha.microservicepropostas.viagem;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;


    @PostMapping("/numeroCartao/{numeroCartao}")
    @Transactional
    public void avisoViagem(@PathVariable String numeroCartao, @RequestHeader(value = "User-Agent") String userAgent,
                            HttpServletRequest request,  @RequestBody @Valid AvisoViagemRequest aviso){

        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        AvisoViagem avisoViagem = aviso.toModel(userAgent, request.getRemoteAddr(), cartao);

        avisoRepository.save(avisoViagem);
    }
}
