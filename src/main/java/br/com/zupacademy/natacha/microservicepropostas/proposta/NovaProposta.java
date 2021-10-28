package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.commons.validator.enums.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    @JoinColumn(name = "numero_cartao")
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

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Cartao getCartao() {
        return cartao;
    }


    public void adicionaStatus(String resultadoSolicitacao) {
        if (resultadoSolicitacao.equals("SEM_RESTRICAO")) {

            this.statusProposta = StatusProposta.ELEGIVEL;
        } else {
            this.statusProposta = StatusProposta.NAO_ELEGIVEL;
        }
    }


    public void associaCartao(Cartao cartao) {
        if (this.statusProposta.equals(StatusProposta.ELEGIVEL)) {
            this.cartao = cartao;
        }
    }
}
