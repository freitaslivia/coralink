package br.com.coralink.api.dto;

import br.com.coralink.api.model.Bairro;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BairroResponseDTO(
        Long id,

        String nome,

        String nomeZona,

        Long idCidade,

        Link link

) {
    public BairroResponseDTO(Bairro bairro) {
        this(bairro.getId(), bairro.getNome(), bairro.getNomeZona(), bairro.getCidade().getId(), null);
    }
}
