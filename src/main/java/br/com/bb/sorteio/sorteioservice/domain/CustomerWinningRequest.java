package br.com.bb.sorteio.sorteioservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWinningRequest {

    @NotNull(message = "Id do sorteio deve ser informada")
    private Long idSorteio;

    @NotNull(message = "Cupom Fiscal deve ser informado")
    @Min(10)
    private Integer nroCupomFiscal;

    @NotNull(message = "CPF deve ser informado")
    @Size(max = 11)
    private String nroCpf;
}
