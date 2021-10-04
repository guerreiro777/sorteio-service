package br.com.bb.sorteio.sorteioservice.domain;

import br.com.bb.sorteio.sorteioservice.validation.OnCreate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SorteioRequest {

    @NotNull(message = "Informe um título para o sorteio", groups = OnCreate.class)
    @Size(min = 3, max = 300, message = "Título deve ter no mínimo 3 e no máximo 300 caracteres")
    private String strTituloSorteio;

    @NotNull(message = "Data do sorteio deve ser informada", groups = OnCreate.class)
    private Date dtSorteio;
}
