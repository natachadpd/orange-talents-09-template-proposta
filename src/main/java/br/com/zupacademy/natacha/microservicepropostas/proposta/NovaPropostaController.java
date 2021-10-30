package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.exceptions.PropostaNaoEncontradaException;
import br.com.zupacademy.natacha.microservicepropostas.proposta.analise.AnaliseFinanceiraClient;
import br.com.zupacademy.natacha.microservicepropostas.proposta.analise.ResultadoAnaliseResponse;
import br.com.zupacademy.natacha.microservicepropostas.proposta.analise.SolicitacaoAnaliseRequest;
import feign.FeignException;
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

    @Autowired
    private AnaliseFinanceiraClient solicitacao;

    @PostMapping
    public ResponseEntity<NovaProposta> criarProposta(@RequestBody @Valid NovaPropostaRequest request,
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
        analiseFinanceira(proposta);
        repository.save(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    private void analiseFinanceira(NovaProposta proposta) {
        try {
            SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest(proposta);
            ResultadoAnaliseResponse response = solicitacao.enviarAnalise(solicitacaoAnaliseRequest);
            proposta.adicionaStatus(response.getResultadoSolicitacao());

        } catch (FeignException.UnprocessableEntity feignException) {
            proposta.adicionaStatus("COM_RESTRICAO");
        }
        catch (FeignException ex) {
            System.out.println(ex.getMessage());
            ex.getMessage();
        }
    }

    @GetMapping("/pesquisar/{id}")
    public NovaPropostaResponse acompanharProposta(@PathVariable long id) {
        NovaProposta proposta = repository.findById(id)
                .orElseThrow(PropostaNaoEncontradaException::new);

        return new NovaPropostaResponse(proposta);
    }
}
