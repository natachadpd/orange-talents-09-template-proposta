package br.com.zupacademy.natacha.microservicepropostas.exceptions;

public class BusinessException extends RuntimeException {

public BusinessException(String message) {
        super(message);
    }
}
