package br.com.zupacademy.natacha.microservicepropostas.proposta.analise;

public class ResultadoAnaliseResponse {


    private String documento;

    private String nome;

    private String resultadoSolicitacao;

    private Long idProposta;


    public ResultadoAnaliseResponse(String documento, String nome,
                                    String resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }
}
