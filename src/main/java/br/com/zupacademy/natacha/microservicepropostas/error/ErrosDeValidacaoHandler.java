package br.com.zupacademy.natacha.microservicepropostas.error;

import br.com.zupacademy.natacha.microservicepropostas.exceptions.BiometriaException;
import br.com.zupacademy.natacha.microservicepropostas.exceptions.BusinessException;
import br.com.zupacademy.natacha.microservicepropostas.exceptions.PropostaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrosDeValidacaoHandler {
    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PropostaNaoEncontradaException.class)
    public Map<String, String> HandlePropostaNaoEncontrada(BusinessException ex){
        Map<String, String> erroResponse = new HashMap<>();
        erroResponse.put("mensagem:",ex.getMessage());
        return erroResponse;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BiometriaException.class)
    public Map<String, String> HandleBiometriaNaoEncontrada(BusinessException ex){
        Map<String, String> erroResponse = new HashMap<>();
        erroResponse.put("mensagem:",ex.getMessage());
        return erroResponse;
    }
}

