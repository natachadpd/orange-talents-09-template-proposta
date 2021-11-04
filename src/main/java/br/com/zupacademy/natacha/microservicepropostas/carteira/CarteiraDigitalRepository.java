package br.com.zupacademy.natacha.microservicepropostas.carteira;

import br.com.zupacademy.natacha.microservicepropostas.commons.enums.Carteira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraDigitalRepository extends CrudRepository<CarteiraDigital, Long> {
    boolean existsByCarteiraAndCartaoNumeroCartao(Carteira carteira, String numeroCartao);
}
