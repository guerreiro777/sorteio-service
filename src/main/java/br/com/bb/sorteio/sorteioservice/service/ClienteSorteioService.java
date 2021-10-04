package br.com.bb.sorteio.sorteioservice.service;

import br.com.bb.sorteio.sorteioservice.domain.CustomerWinningRequest;
import br.com.bb.sorteio.sorteioservice.entity.Cliente;
import br.com.bb.sorteio.sorteioservice.entity.ClienteSorteio;
import br.com.bb.sorteio.sorteioservice.entity.Sorteio;
import br.com.bb.sorteio.sorteioservice.exception.EnumBussinesException;
import br.com.bb.sorteio.sorteioservice.repository.ClienteRepository;
import br.com.bb.sorteio.sorteioservice.repository.ClienteSorteioRepository;
import br.com.bb.sorteio.sorteioservice.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteSorteioService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SorteioRepository sorteioRepository;

    @Autowired
    ClienteSorteioRepository clienteSorteioRepository;

    @Transactional
    public ClienteSorteio save(final CustomerWinningRequest customerWinningRequest) {
        final Optional<Cliente> cliente = clienteRepository.findByNroCpf(customerWinningRequest.getNroCpf());
        if (cliente.isEmpty()) {
            throw EnumBussinesException.NOT_FOUND.exception();
        }
        if (!cliente.get().getNroCupomFiscal().equals(customerWinningRequest.getNroCupomFiscal())) {
            throw EnumBussinesException.NRO_CUPOM_NAO_ENCONTRADO.exception();
        }
        final Optional<Sorteio> sorteio = sorteioRepository.findById(customerWinningRequest.getIdSorteio());
        if (sorteio.isEmpty()) {
            throw EnumBussinesException.SORTEIO_NAO_ENCONTRADO.exception();
        }

        ClienteSorteio clienteSorteio = new ClienteSorteio();
        clienteSorteio.setCliente(cliente.get());
        clienteSorteio.setSorteio(sorteio.get());
        clienteSorteio.setDtCadastro(new Date());
        return clienteSorteioRepository.save(clienteSorteio);
    }
}
