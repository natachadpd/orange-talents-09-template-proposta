package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.commons.validator.DocumentoValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @DocumentoValidator(domainClass = NovaProposta.class, fieldName = "documento")
    @Length(min = 11, max = 18)
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(@NotBlank @Length(min = 11, max = 18) String documento,
                               @Email @NotBlank String email, @NotBlank String nome,
                               @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public NovaProposta toModel() {
        return new NovaProposta(documento, email, nome, endereco, salario);
    }

    public String getDocumento() {
        return documento;
    }
}
