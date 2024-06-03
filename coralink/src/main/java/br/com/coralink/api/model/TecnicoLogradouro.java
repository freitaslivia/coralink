package br.com.coralink.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CORALINK_TECNICO_LOGRADOURO")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TecnicoLogradouro {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_tecnico_logradouro",
            allocationSize = 1)
    @Column(name = "pk_id_tecnico_logradouro")
    private Long id;

    @Column(name = "ds_tipo_logradouro",  columnDefinition = "VARCHAR(50)", nullable = false)
    private String tipo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_logradouro", nullable = false)
    private Logradouro logradouro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_tecnico", nullable = false)
    private Tecnico tecnico;
}
