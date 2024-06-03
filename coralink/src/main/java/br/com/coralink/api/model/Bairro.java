package br.com.coralink.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CORALINK_BAIRRO")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Bairro {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_bairro",
            allocationSize = 1)
    @Column(name = "pk_id_bairro")
    private Long id;

    @Column(name = "nm_bairro",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nm_zona_bairro",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nomeZona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_cidade", nullable = false)
    @JsonIgnore
    private Cidade cidade;
}
