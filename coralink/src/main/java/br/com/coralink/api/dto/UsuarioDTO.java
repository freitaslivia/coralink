package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Usuario;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record UsuarioDTO(

        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 50, message = "O campo pode ter no máximo 50 caracteres")
        String nome,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "A senha deve conter pelo menos um caractere especial e uma letra maiúscula")
        @Size(min = 12, message = "A senha deve ter no minimo 12 caracteres")
        String senha,

        @NotBlank(message = "Senha de confirmação deve ser informada")
        String senhaConfirmacao,

        @NotBlank(message = "O campo não pode estar vazio")
        @Email
        @Pattern(regexp = "^[a-z]*$", message = "O email deve conter apenas letras minúsculas")
        String email
) {

        public UsuarioDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getSenha(), usuario.getEmail());
        }
}