package br.com.zupacademy.natacha.microservicepropostas.proposta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends CrudRepository<NovaProposta, Long> {
}
