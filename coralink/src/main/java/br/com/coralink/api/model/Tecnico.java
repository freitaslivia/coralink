package br.com.coralink.api.model;

import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.TecnicoDTO;
import br.com.coralink.api.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity

@Table(name = "CORALINK_TECNICO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_TELEFONE", columnNames = {"nr_tel_principal"})
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_tecnico",
            allocationSize = 1)
    @Column(name = "pk_id_tecnico",  columnDefinition = "NUMBER(10)")
    private Long id;

    @Column(name = "nm_tecnico", columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nr_tel_principal", columnDefinition = "char(13)", nullable = false)
    private String telefone;

    @Column(name = "nm_empresa", columnDefinition = "VARCHAR(100)", nullable = true)
    private String nomeEmpresa;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    public Tecnico(TecnicoDTO dadosTecnico, Usuario usuario) {
        this.nome = dadosTecnico.nome();
        this.telefone = dadosTecnico.telefone();
        this.nomeEmpresa = dadosTecnico.nomeEmpresa();
        this.usuario = usuario;
    }

}
