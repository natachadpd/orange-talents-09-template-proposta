package br.com.zupacademy.natacha.microservicepropostas.proposta;

import br.com.zupacademy.natacha.microservicepropostas.cartao.Cartao;
import br.com.zupacademy.natacha.microservicepropostas.commons.validator.enums.StatusProposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends CrudRepository<NovaProposta, Long> {
    Optional<NovaProposta> findByDocumento(String documento);

    @Query(value = "SELECT p.* FROM nova_proposta p " +
                    "WHERE p.status_proposta = 'ELEGIVEL' AND p.cartao_id IS NULL ",
            nativeQuery = true)
    List<NovaProposta> findBySatusAndCartaoId();
}
