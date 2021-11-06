package br.com.zupacademy.natacha.microservicepropostas.carteira;

import br.com.zupacademy.natacha.microservicepropostas.MicroservicePropostasApplication;
import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.natacha.microservicepropostas.commons.client.CarteiraDigitalClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CarteiraDigitalController {



    @Autowired
    private CarteiraDigitalRepository carteiraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraDigitalClient carteiraClient;


    @PostMapping("/{numeroCartao}/carteiras")
    @Transactional
    public ResponseEntity<?> associar(@PathVariable String numeroCartao,
                                      @RequestBody @Valid CarteiraDigitalRequest request,
                                      UriComponentsBuilder uriBuilder) {


        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartão não encontrado"));

        CarteiraDigital carteira = request.toModel(cartao);

        if(carteiraRepository
                .existsByCarteiraAndCartaoNumeroCartao(carteira.getCarteira(), numeroCartao)){
            throw new ResponseStatusException(HttpStatus
                    .UNPROCESSABLE_ENTITY, "Cartão já cadastrado para esta carteira.");
        }

        vincularCarteiraDigital(numeroCartao, carteira);


        URI uri = uriBuilder.path("cartoes/{numeroCartao}/carteiras/{id}")
                .buildAndExpand(cartao.getNumeroCartao(), carteira.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    public void vincularCarteiraDigital(String numeroCartao, CarteiraDigital carteiraDigital) {

        try {
            CarteiraDigitalRequest carteiraRequest = new CarteiraDigitalRequest(carteiraDigital);
            carteiraClient.associarCarteira(numeroCartao, carteiraRequest);
            carteiraRepository.save(carteiraDigital);
        } catch (FeignException.UnprocessableEntity feignException) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Não foi possível associar o cartão a carteira.");
        } catch (FeignException ex) {
            System.out.println(ex.getMessage());
            ex.getMessage();
        }

    }
}
