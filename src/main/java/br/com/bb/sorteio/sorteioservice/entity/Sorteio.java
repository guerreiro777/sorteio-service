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
@Table(name = "sorteio")
public class Sorteio {

    @Id
    @Column(name = "id_sorteio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSorteio;

    @Column(name = "dt_sorteio", nullable = false)
    private Date dtSorteio;

    @Column(name = "str_titulo_sorteio", length = 300, nullable = false)
    private String strTituloSorteio;

    @Column(name = "st_sorteio", length = 1, nullable = false)
    private Integer stSorteio;

}
