package br.com.bb.sorteio.sorteioservice.repository;


import br.com.bb.sorteio.sorteioservice.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByNroCpf(final String nroCpf);
}
