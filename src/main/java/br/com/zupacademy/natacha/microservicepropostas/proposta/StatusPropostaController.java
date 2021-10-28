package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.exceptions.PropostaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propostas")
public class StatusPropostaController {

    @Autowired
    private NovaPropostaRepository repository;

    @GetMapping("/{id}")
    public NovaProposta getById(@PathVariable long id){
        return repository.findById(id).orElseThrow(PropostaNaoEncontradaException::new);
    }

}
