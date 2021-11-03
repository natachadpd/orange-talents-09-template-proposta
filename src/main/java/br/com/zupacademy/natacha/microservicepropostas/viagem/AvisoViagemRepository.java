package br.com.zupacademy.natacha.microservicepropostas.viagem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends CrudRepository<AvisoViagem, Long> {
}
