package br.com.zupacademy.natacha.microservicepropostas.cartao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioCartaoRepository extends CrudRepository<BloqueioCartao, Long> {
    boolean existsByCartaoNumeroCartao(String numeroCartao);
}
