package br.com.coralink.api.dto;

import br.com.coralink.api.model.Cidade;
import br.com.coralink.api.model.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CidadeDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome da Cidade", example = "São Paulo")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(min = 3, max = 3, message = "O campo deve ter exatamente 3 caracteres")
        @Pattern(regexp = "[0-9]+", message = "Deve ter somente números")
        @Schema(description = "DDD da Cidade", example = "011")
        String ddd,

        @Schema(description = "ID do Estado", example = "1")
        Long idEstado
) {
    public CidadeDTO(Cidade cidade) {
        this(cidade.getId(), cidade.getNome(), cidade.getDdd(), cidade.getEstado().getId());
    }
}
