package br.com.coralink.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "CORALINK_ESTADO")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_estado",
            allocationSize = 1)
    @Column(name = "pk_id_estado")
    private Long id;

    @Column(name = "sg_estado",  columnDefinition = "char(2)", nullable = false)
    private String sigla;

    @Column(name = "nm_estado",  columnDefinition = "VARCHAR(30)", nullable = false)
    private String nome;

}
