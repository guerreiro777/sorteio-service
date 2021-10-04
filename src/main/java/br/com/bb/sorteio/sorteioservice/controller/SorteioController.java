package br.com.bb.sorteio.sorteioservice.controller;

import br.com.bb.sorteio.sorteioservice.domain.CustomerWinningRequest;
import br.com.bb.sorteio.sorteioservice.domain.SorteioRequest;
import br.com.bb.sorteio.sorteioservice.entity.Sorteio;
import br.com.bb.sorteio.sorteioservice.service.SorteioService;
import br.com.bb.sorteio.sorteioservice.validation.OnCreate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/v1/sorteio")
@Log
public class SorteioController {

    @Autowired
    SorteioService sorteioService;

    @ApiOperation(value = "Retorna um sorteio", response = Sorteio.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    public Sorteio getSorteio(@PathVariable @NotNull long id) {
        return sorteioService.findById(id);
    }

    @ApiOperation(value = "Cria um sorteio", response = Sorteio.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public Sorteio createSorteio(@Validated(value = OnCreate.class) @RequestBody SorteioRequest sorteioRequest) {
        return sorteioService.save(sorteioRequest);
    }

    @ApiOperation(value = "Atualiza um sorteio", response = Sorteio.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    public Sorteio updateSorteio(@PathVariable @NotNull long id, @Valid @RequestBody SorteioRequest sorteioRequest) {
        return sorteioService.update(id, sorteioRequest);
    }

    @ApiOperation(value = "Deleta um sorteio", response = ResponseEntity.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSorteio(@PathVariable @NotNull long id) {
        sorteioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
