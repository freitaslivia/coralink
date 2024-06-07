package br.com.coralink.api.dto;

import br.com.coralink.api.model.Cidade;
import org.springframework.hateoas.Link;

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
