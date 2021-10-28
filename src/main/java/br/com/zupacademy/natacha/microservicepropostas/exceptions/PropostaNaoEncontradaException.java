package br.com.zupacademy.natacha.microservicepropostas.exceptions;


public class PropostaNaoEncontradaException extends  BusinessException {

public PropostaNaoEncontradaException() {
        super("Proposta não encontrada!");
    }
}
