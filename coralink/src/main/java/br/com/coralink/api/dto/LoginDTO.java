package br.com.coralink.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Email deve ser informado")
        @Email
        @Schema(description = "Email", example = "livia@gmail.com")
        String email,
        @NotBlank(message = "Senha deve ser informada")
        @Schema(description = "Senha", example = "No101035566.")
        String senha
) {



}
