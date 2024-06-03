package br.com.coralink.api.model;

import br.com.coralink.api.dto.EmpresaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Table(name = "CORALINK_EMPRESA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CNPJ", columnNames = {"nr_cnpj"}),
        @UniqueConstraint(name = "UK_EMAIL", columnNames = {"ds_email"})
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_empresa",
            allocationSize = 1)
    @Column(name = "pk_id_empresa")
    private Long id;

    @Column(name = "nm_empresa", columnDefinition = "VARCHAR(100)", nullable = false)
    private String nome;

    @Column(name = "nm_exibicao", columnDefinition = "VARCHAR(100)", nullable = false)
    private String nomeExibicao;

    @Column(name = "nr_cnpj",  columnDefinition = "char(14)", nullable = false)
    private String cnpj;

    @Column(name = "ds_email", columnDefinition = "VARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "nr_tel_principal",  columnDefinition = "char(13)", nullable = false)
    private String telefone;

    @Column(name = "tp_empresa",  columnDefinition = "VARCHAR(20)", nullable = false)
    private String tpEmpresa;

    /*
    @Column(name = "tp_empresa", columnDefinition = "VARCHAR(20)", nullable = true)
    private String infos;
    */

    @OneToMany(mappedBy = "empresa")
    //@JoinColumn(nullable = true)
    private List<EmpresaLogradouro> empresaLogradouros;

    public Empresa(EmpresaDTO dadosEmpresa) {
        this.nome = dadosEmpresa.nome();
        this.nomeExibicao = dadosEmpresa.nomeExibicao();
        this.cnpj = dadosEmpresa.cnpj();
        this.email = dadosEmpresa.email();
        this.telefone = dadosEmpresa.telefone();
        this.tpEmpresa = dadosEmpresa.tpEmpresa();
    }
}
