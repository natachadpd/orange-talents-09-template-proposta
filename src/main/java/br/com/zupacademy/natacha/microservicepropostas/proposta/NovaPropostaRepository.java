package br.com.zupacademy.natacha.microservicepropostas.proposta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends CrudRepository<NovaProposta, Long> {
    Optional<NovaProposta> findByDocumento(String documento);

    @Query(value = "SELECT p.* FROM nova_proposta p " +
            "WHERE p.status_proposta = 'ELEGIVEL' AND p.numero_cartao IS NULL ",
            nativeQuery = true)
    List<NovaProposta> findBySatusAndCartaoId();

}