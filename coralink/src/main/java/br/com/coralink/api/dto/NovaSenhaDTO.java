package br.com.coralink.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NovaSenhaDTO(
        @NotBlank(message = "Email deve ser informado")
        @Schema(description = "Email", example = "Livia@gmail.com")
        @Email
        String email,
        @NotBlank(message = "Senha velha deve ser informado")
        String senhaAtual,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "A senha deve conter pelo menos um caractere especial e uma letra maiúscula")
        @Size(max = 15, min = 12, message = "A senha deve ter no minimo 12 e maximo de 15 caracteres")
        @Schema(description = "A senha deve ter no minimo 12 e maximo de 15 caracteres", example = "No101035566.")
        String senhaNova,
        @NotBlank(message = "Senha confirmação deve ser informado")
        @Schema(description = "Senha Confirmação", example = "No101035566.")
        String senhaConfirmacao
) {

}