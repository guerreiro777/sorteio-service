package br.com.bb.sorteio.sorteioservice.repository;

import br.com.bb.sorteio.sorteioservice.entity.ClienteSorteio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteSorteioRepository extends CrudRepository<ClienteSorteio, Long> {
}
