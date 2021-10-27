package br.com.zupacademy.natacha.microservicepropostas.proposta.analise;

import br.com.zupacademy.natacha.microservicepropostas.proposta.NovaProposta;

public class SolicitacaoAnaliseRequest {

    private String documento;
    private String nome;
    private Long idProposta;

    @Deprecated
    public SolicitacaoAnaliseRequest() {
    }

    public SolicitacaoAnaliseRequest(NovaProposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }


    public Long getIdProposta() {
        return idProposta;
    }
}
