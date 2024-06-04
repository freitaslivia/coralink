package br.com.coralink.api.model;

import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "CORALINK_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = {"ds_email"})
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_empresa",
            allocationSize = 1)
    @Column(name = "pk_id_empresa",  columnDefinition = "NUMERIC(10)")
    private Long id;

    @Column(name = "nm_exibicao", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "ds_email", columnDefinition = "VARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "ds_senha",  columnDefinition = "VARCHAR(15)", nullable = false)
    private String senha;

    public Usuario(UsuarioDTO dadosUsuarios) {
        this.nome = dadosUsuarios.nome();
        this.email = dadosUsuarios.email();
        this.senha = dadosUsuarios.senha();
    }
}
