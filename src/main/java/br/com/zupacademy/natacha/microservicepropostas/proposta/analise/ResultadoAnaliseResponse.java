package br.com.zupacademy.natacha.microservicepropostas.proposta.analise;

import javax.validation.constraints.NotBlank;

public class ResultadoAnaliseResponse {


    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String resultadoSolicitacao;

    @NotBlank
    private String idProposta;


    public ResultadoAnaliseResponse(@NotBlank String documento, @NotBlank String nome,
                                    @NotBlank String resultadoSolicitacao,
                                    @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }


    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }


}
