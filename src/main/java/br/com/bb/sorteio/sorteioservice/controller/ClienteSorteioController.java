package br.com.bb.sorteio.sorteioservice.controller;

import br.com.bb.sorteio.sorteioservice.domain.CustomerWinningRequest;
import br.com.bb.sorteio.sorteioservice.entity.Cliente;
import br.com.bb.sorteio.sorteioservice.entity.ClienteSorteio;
import br.com.bb.sorteio.sorteioservice.entity.Sorteio;
import br.com.bb.sorteio.sorteioservice.service.AsyncMessageService;
import br.com.bb.sorteio.sorteioservice.service.ClienteSorteioService;
import br.com.bb.sorteio.sorteioservice.service.SorteioService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/cliente-sorteio")
@Log
public class ClienteSorteioController {

    @Autowired
    ClienteSorteioService clienteSorteioService;

    @Autowired
    SorteioService sorteioService;

    @Autowired
    AsyncMessageService asyncMessageService;

    @ApiOperation(value = "Cadastra o vencedor do sorteio", response = ClienteSorteio.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ClienteSorteio customerWinning(@Valid @RequestBody CustomerWinningRequest customerWinningRequest) {
        // Cadastra o cliente sorteado
        ClienteSorteio clienteSorteio = clienteSorteioService.save(customerWinningRequest);
        // Finaliza o sorteio
        sorteioService.finishDraw(clienteSorteio.getSorteio().getIdSorteio());
        // Envia mensagem ao vencedor
        Cliente cliente = clienteSorteio.getCliente();
        asyncMessageService.sendSmsMessageWinningCustomer(cliente);
        asyncMessageService.sendEmailMessageWinningCustomer(cliente);

        return clienteSorteio;
    }
}
