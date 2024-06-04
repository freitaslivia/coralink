package br.com.coralink.api.model;

import br.com.coralink.api.dto.EstadoDTO;
import br.com.coralink.api.dto.LogradouroDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "CORALINK_ESTADO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_SG_ESTADO", columnNames = {"sg_estado"}),
        @UniqueConstraint(name = "UK_NM_ESTADO", columnNames = {"nm_estado"})
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_estado",
            allocationSize = 1)
    @Column(name = "pk_id_estado",  columnDefinition = "NUMERIC(10)")
    private Long id;

    @Column(name = "sg_estado",  columnDefinition = "char(2)", nullable = false)
    private String sigla;

    @Column(name = "nm_estado",  columnDefinition = "VARCHAR(30)", nullable = false)
    private String nome;

    public Estado(EstadoDTO dadosEstado) {
        this.nome = dadosEstado.nome();
        this.sigla = dadosEstado.sigla();
    }

}
