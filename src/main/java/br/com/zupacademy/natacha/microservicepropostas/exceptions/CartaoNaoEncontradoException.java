package br.com.zupacademy.natacha.microservicepropostas.exceptions;

public class CartaoNaoEncontradoException extends BusinessException{
    public CartaoNaoEncontradoException() {
        super("Cartão não encontrado!");
    }
}
