package br.com.zupacademy.natacha.microservicepropostas.biometria;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.cartao.CartaoRepository;
import br.com.zupacademy.natacha.microservicepropostas.exceptions.BiometriaException;
import br.com.zupacademy.natacha.microservicepropostas.exceptions.CartaoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/inserir")
    @Transactional
    public ResponseEntity<?> cadastrarBiometria(@RequestParam String numeroCartao,
                                                @RequestBody @Valid BiometriaRequest request,
                                                UriComponentsBuilder uriBuilder){
        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow(CartaoNaoEncontradoException::new);

        Biometria biometria = request.toModel(cartao);
        biometria.base64Biometria(request.getImpressaoDigital());
        biometriaRepository.save(biometria);

        URI uri = uriBuilder.path("/{id}")
                .buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
