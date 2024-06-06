package br.com.coralink.api.dto;

import br.com.coralink.api.model.Cidade;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CidadeResponseDTO(
        Long id,

        String nome,

        String ddd,

        Long idEstado,

        Link link
) {
    public CidadeResponseDTO(Cidade cidade) {
        this(cidade.getId(), cidade.getNome(), cidade.getDdd(), cidade.getEstado().getId(), null);
    }
}
