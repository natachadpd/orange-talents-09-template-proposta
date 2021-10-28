package br.com.zupacademy.natacha.microservicepropostas.proposta.analise;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnaliseRequest {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    @Deprecated
    public SolicitacaoAnaliseRequest() {
    }

    public SolicitacaoAnaliseRequest(NovaProposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
