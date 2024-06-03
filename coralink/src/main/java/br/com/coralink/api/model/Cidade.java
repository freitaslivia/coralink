package br.com.coralink.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CORALINK_CIDADE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cidade {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_cidade",
            allocationSize = 1)
    @Column(name = "pk_id_cidade")
    private Long id;

    @Column(name = "nm_cidade",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nr_ddd",  columnDefinition = "VARCHAR(3)", nullable = false)
    private String ddd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_estado", nullable = false)
    @JsonIgnore
    private Estado estado;
}
