package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record UsuarioDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 50, message = "O campo pode ter no máximo 50 caracteres")
        @Schema(description = "Nome", example = "Livia")
        String nome,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "A senha deve conter pelo menos um caractere especial e uma letra maiúscula")
        @Size(max = 15, min = 12, message = "A senha deve ter no minimo 12 e maximo de 15 caracteres")
        @Schema(description = "A senha deve ter no minimo 12 e maximo de 15 caracteres", example = "No101035566.")
        String senha,

        @NotBlank(message = "Senha de confirmação deve ser informada")
        String senhaConfirmacao,

        @NotBlank(message = "O campo não pode estar vazio")
        @Email
        @Schema(description = "Email", example = "livia@gmail.com")
        String email
) {
        public UsuarioDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getSenha(), usuario.getEmail());
        }
}