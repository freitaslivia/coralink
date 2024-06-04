package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Logradouro;
import br.com.coralink.api.model.Tecnico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LogradouroDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome", example = "R. Fidêncio Ramos")
        String nome,


        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{8}$", message = "Deve ter somente números e 8 digitos")
        @Schema(description = "CEP", example = " 04551000")
        String cep,

        @NotBlank(message = "O campo não pode estar vazio")
        @Schema(description = "Tipo de logradouro", example = "Residencial")
        String tipo,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        @Schema(description = "ID do Bairro", example = "1")
        Long idBairro


) {
    public LogradouroDTO(Logradouro logradouro) {
        this(logradouro.getId(), logradouro.getNome(), logradouro.getCep(), logradouro.getTipo(), logradouro.getBairro().getId());
    }
}
