package br.com.zupacademy.natacha.microservicepropostas.viagem;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.natacha.microservicepropostas.commons.client.AvisoViagemClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/viagens")
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemClient avisoViagemClient;

    @PostMapping("/numeroCartao/{numeroCartao}/avisos")
    @Transactional
    public void avisoViagem(@PathVariable String numeroCartao, @RequestHeader(value = "User-Agent") String userAgent,
                            HttpServletRequest http,  @RequestBody AvisoViagemRequest request){

        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        AvisoViagem avisoViagem = request.toModel(userAgent, http.getRemoteAddr(), cartao);

        avisoViagemCartao(cartao, avisoViagem);
    }

    private void avisoViagemCartao(Cartao cartao, AvisoViagem avisoViagem) {

        try{
            AvisoViagemSolicitacaoRequest solicitacaoRequest = new AvisoViagemSolicitacaoRequest(avisoViagem.getDestino(),
                    avisoViagem.getDataTermino());
            avisoViagemClient.avisoViagem(cartao.getNumeroCartao(), solicitacaoRequest);

            avisoRepository.save(avisoViagem);}
        catch (FeignException.UnprocessableEntity feignException) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível notificar o serviço externo.");
        }
        catch (FeignException ex) {
            System.out.println(ex.getMessage());
            ex.getMessage();
        }
    }
}
