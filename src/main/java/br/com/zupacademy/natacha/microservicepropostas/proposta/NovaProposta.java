package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.commons.validator.enums.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class NovaProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
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

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;


    @Deprecated
    public NovaProposta() {
    }

    public NovaProposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
                        @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void adicionaStatus(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public Cartao getNumeroCartao(){
        return getNumeroCartao();
    }



    public void associaCartao(Cartao cartao) {
        if(this.statusProposta.equals(StatusProposta.ELEGIVEL)){
           this.cartao = cartao;
        }


    }
}
