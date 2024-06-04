package br.com.coralink.api.dto;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Cidade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BairroDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome do Bairro", example = "Tatuapé")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Zona do Bairro", example = "Zona leste")
        String nomeZona,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        @Schema(description = "ID da Cidade", example = "Residencial")
        Long idCidade

) {
    public BairroDTO(Bairro bairro) {
        this(bairro.getId(), bairro.getNome(), bairro.getNomeZona(), bairro.getCidade().getId());
    }
}
