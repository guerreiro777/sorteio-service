package br.com.bb.sorteio.sorteioservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente_sorteio")
public class ClienteSorteio {

    @Id
    @Column(name = "id_cliente_sorteio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClienteSorteio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sorteio", nullable = false)
    private Sorteio sorteio;

    @Column(name = "dt_cadastro", nullable = false)
    private Date dtCadastro;
}
