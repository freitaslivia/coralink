package br.com.coralink.api.dto;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Cidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BairroDTO(
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nomeZona,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        Long idCidade

) {
    public BairroDTO(Bairro bairro) {
        this(bairro.getId(), bairro.getNome(), bairro.getNomeZona(), bairro.getCidade().getId());
    }
}
