package br.com.bb.sorteio.sorteioservice.service;

import br.com.bb.sorteio.sorteioservice.entity.Cliente;
import br.com.bb.sorteio.sorteioservice.repository.ClienteRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public void save(final String clienteMessage) {
        JsonObject objJsonCliente = new Gson().fromJson(clienteMessage, JsonObject.class);
        final String nmCliente = objJsonCliente.get("nmCliente").getAsString();
        final String nroDdd = objJsonCliente.get("nroDdd").getAsString();
        final String nroTelefone = objJsonCliente.get("nroTelefone").getAsString();
        final String nroCpf = objJsonCliente.get("nroCpf").getAsString();
        final String strEmail = objJsonCliente.get("strEmail").getAsString();
        final String nroCupomFiscal = objJsonCliente.get("nroCupomFiscal").getAsString();
        // Não cadastra o cliente 2x
        if (clienteRepository.findByNroCpf(nroCpf).isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNroCpf(nroCpf);
            cliente.setStrEmail(strEmail);
            cliente.setNroCupomFiscal(Integer.parseInt(nroCupomFiscal));
            cliente.setNmCliente(nmCliente);
            cliente.setNroDdd(Integer.parseInt(nroDdd));
            cliente.setNroTelefone(Integer.parseInt(nroTelefone));
            clienteRepository.save(cliente);
            log.info("Cliente cadastrado com sucesso: " + clienteMessage);
        } else {
            log.info("Cliente já cadastrado: " + clienteMessage);
        }

    }


}
