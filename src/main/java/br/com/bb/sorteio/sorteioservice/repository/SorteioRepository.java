package br.com.bb.sorteio.sorteioservice.repository;


import br.com.bb.sorteio.sorteioservice.entity.Sorteio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends CrudRepository<Sorteio, Long> {

}
