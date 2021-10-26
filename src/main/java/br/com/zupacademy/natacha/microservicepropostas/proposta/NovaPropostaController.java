package br.com.zupacademy.natacha.microservicepropostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {


    @Autowired
    private NovaPropostaRepository repository;

    @PostMapping
    public ResponseEntity<?> criarProposta(@RequestBody @Valid NovaPropostaRequest request,
                                           UriComponentsBuilder uriBuilder) {

        Optional<NovaProposta> documento = repository.findByDocumento(request.getDocumento());

        if (documento.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Proposta já criada para este documento. Não podemos ter mais de uma " +
                            "proposta para o mesmo documento. "
            );
        }

        NovaProposta proposta = request.toModel();
        repository.save(proposta);
        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
