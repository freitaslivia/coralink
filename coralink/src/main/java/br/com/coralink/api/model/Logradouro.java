package br.com.coralink.api.model;

import br.com.coralink.api.dto.LogradouroDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CORALINK_LOGRADOURO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Logradouro {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_logradouro",
            allocationSize = 1)
    @Column(name = "pk_id_logradouro",  columnDefinition = "NUMBER(10)")
    private Long id;

    @Column(name = "nm_logradouro",  columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nr_cep",  columnDefinition = "char(8)", nullable = false)
    private String cep;

    @Column(name = "ds_tipo_logradouro",  columnDefinition = "VARCHAR(50)", nullable = false)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_bairro", nullable = false)
    @JsonIgnore
    private Bairro bairro;

    @ManyToMany
    @JoinTable(name = "CORALINK_TECNICO_LOGRADOURO",
            joinColumns = @JoinColumn(name = "pk_id_logradouro"),
            inverseJoinColumns = @JoinColumn(name = "pk_id_tecnico"))
    private List<Tecnico> tecnicos;

    @ManyToMany
    @JoinTable(name = "CORALINK_TECNICO_EMPRESA",
            joinColumns = @JoinColumn(name = "pk_id_logradouro"),
            inverseJoinColumns = @JoinColumn(name = "pk_id_empresa"))
    private List<Empresa> empresas;

    public Logradouro(LogradouroDTO dadosLogradouro, Bairro bairro) {
        this.nome = dadosLogradouro.nome();
        this.cep = dadosLogradouro.cep();
        this.tipo = dadosLogradouro.tipo();
        this.bairro = bairro;

    }
}
