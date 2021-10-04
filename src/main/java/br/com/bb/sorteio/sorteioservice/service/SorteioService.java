package br.com.bb.sorteio.sorteioservice.service;

import br.com.bb.sorteio.sorteioservice.domain.SorteioRequest;
import br.com.bb.sorteio.sorteioservice.entity.Sorteio;
import br.com.bb.sorteio.sorteioservice.exception.EnumBussinesException;
import br.com.bb.sorteio.sorteioservice.repository.SorteioRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SorteioService {

    @Autowired
    SorteioRepository sorteioRepository;

    @Transactional
    public Sorteio save(final SorteioRequest sorteioRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Sorteio sorteio = modelMapper.map(sorteioRequest, Sorteio.class);
        sorteio.setStSorteio(1);
        return sorteioRepository.save(sorteio);
    }

    @Transactional
    public Sorteio update(final long id, final SorteioRequest sorteioRequest) {
        Sorteio sorteio = this.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setSkipNullEnabled(true);
        modelMapper.map(sorteioRequest, sorteio);
        return sorteioRepository.save(sorteio);
    }

    public Sorteio findById(final Long id) {
        Optional<Sorteio> sorteio = sorteioRepository.findById(id);
        if (sorteio.isEmpty()) {
            throw EnumBussinesException.NOT_FOUND.exception();
        }
        return sorteio.get();
    }

    @Transactional
    public void delete(final Long id) {
        Sorteio sorteio = this.findById(id);
        sorteioRepository.delete(sorteio);
    }

    @Transactional
    public void finishDraw(final Long id) {
        Sorteio sorteio = this.findById(id);
        sorteio.setStSorteio(0);
        sorteioRepository.save(sorteio);
    }
}
