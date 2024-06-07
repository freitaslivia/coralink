package br.com.coralink.api.model;

import br.com.coralink.api.dto.CidadeDTO;
import br.com.coralink.api.dto.LogradouroDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CORALINK_CIDADE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_cidade",
            allocationSize = 1)
    @Column(name = "pk_id_cidade",  columnDefinition = "NUMBER(10)")
    private Long id;

    @Column(name = "nm_cidade",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nr_ddd",  columnDefinition = "VARCHAR(3)", nullable = false)
    private String ddd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_estado", nullable = false)
    @JsonIgnore
    private Estado estado;

    public Cidade(CidadeDTO dadosCidade, Estado estado) {
        this.nome = dadosCidade.nome();
        this.ddd = dadosCidade.ddd();
        this.estado = estado;
    }
}
