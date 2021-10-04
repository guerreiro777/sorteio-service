package br.com.bb.sorteio.sorteioservice.service;

import br.com.bb.sorteio.sorteioservice.domain.message.ClienteMessage;
import br.com.bb.sorteio.sorteioservice.entity.Cliente;
import br.com.bb.sorteio.sorteioservice.utils.JsonUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
@Log
public class AsyncMessageService {

    private static final String SMS_QUEUE_NAME = "send-sms-winning-customer";
    private static final String EMAIL_QUEUE_NAME = "send-email-winning-customer";

    @Autowired
    JmsTemplate jmsTemplate;

    private void createQueueInJsonFormat(final String queueName, final Object objectMessage) {
        String jsonMessage = JsonUtil.objectToJson(objectMessage);
        jmsTemplate.convertAndSend(queueName, jsonMessage);
        log.log(Level.INFO, "Fila cadastrada: " + queueName + " " + jsonMessage);
    }

    public void sendSmsMessageWinningCustomer(final Cliente cliente) {
        ClienteMessage clienteMessage = new ClienteMessage();
        clienteMessage.setNmCliente(cliente.getNmCliente());
        clienteMessage.setNroCpf(cliente.getNroCpf());
        clienteMessage.setNroDdd(cliente.getNroDdd());
        clienteMessage.setNroTelefone(cliente.getNroTelefone());
        clienteMessage.setNroCupomFiscal(cliente.getNroCupomFiscal());
        this.createQueueInJsonFormat(SMS_QUEUE_NAME, clienteMessage);
    }

    public void sendEmailMessageWinningCustomer(final Cliente cliente) {
        ClienteMessage clienteMessage = new ClienteMessage();
        clienteMessage.setNmCliente(cliente.getNmCliente());
        clienteMessage.setNroCpf(cliente.getNroCpf());
        clienteMessage.setStrEmail(cliente.getStrEmail());
        clienteMessage.setNroCupomFiscal(cliente.getNroCupomFiscal());
        this.createQueueInJsonFormat(EMAIL_QUEUE_NAME, clienteMessage);
    }

}
