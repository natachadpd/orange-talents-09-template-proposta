package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.commons.validator.enums.StatusProposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NovaPropostaResponse {

    @JsonProperty
    private String documento;
    @JsonProperty
    private String email;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String endereco;
    @JsonProperty
    private BigDecimal salario;
    @JsonProperty
    private StatusProposta statusProposta;
    @JsonProperty
    private String numeroCartao;


    public NovaPropostaResponse(NovaProposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.statusProposta = proposta.getStatusProposta();
        this.numeroCartao = proposta.getCartao() == null ? "-"
                : proposta.getCartao().getNumeroCartao() ;
    }


}
