package br.com.bb.sorteio.sorteioservice.component;

import br.com.bb.sorteio.sorteioservice.service.ClienteService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Log
@Component
public class ClienteRegisteredController {

    private static final String QUEUE_NAME = "registered-customer";

    @Autowired
    ClienteService clienteService;

    @JmsListener(destination = QUEUE_NAME, containerFactory = "myFactory")
    public void listenMessageFromRegisteredCustomer(final String cliente) {
        log.log(Level.INFO, "Fila recebida: " + QUEUE_NAME + " " + cliente);
        clienteService.save(cliente);
    }
}
