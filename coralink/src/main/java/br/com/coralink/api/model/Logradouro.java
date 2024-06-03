package br.com.coralink.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "CORALINK_LOGRADOURO")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Logradouro {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_logradouro",
            allocationSize = 1)
    @Column(name = "pk_id_logradouro")
    private Long id;

    @Column(name = "nm_logradouro",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nr_cep",  columnDefinition = "char(8)", nullable = false)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_bairro", nullable = false)
    @JsonIgnore
    private Bairro bairro;

    @OneToMany(mappedBy = "logradouro")
    //@JoinColumn(nullable = true)
    private List<TecnicoLogradouro> tecnicoLogradouros;

    @OneToMany(mappedBy = "logradouro")
    //@JoinColumn(nullable = true)
    private List<EmpresaLogradouro> empresaLogradouros;
}
