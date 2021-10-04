package br.com.bb.sorteio.sorteioservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nm_cliente", length = 300, nullable = false)
    private String nmCliente;

    @Column(name = "nro_cpf", length = 11, nullable = false)
    private String nroCpf;

    @Column(name = "str_email", length = 50, nullable = false)
    private String strEmail;

    @Column(name = "nro_ddd", length = 2, nullable = false)
    private Integer nroDdd;

    @Column(name = "nro_telefone", length = 9, nullable = false)
    private Integer nroTelefone;

    @Column(name = "nro_cupom_fiscal", length = 10, nullable = false)
    private Integer nroCupomFiscal;
}
