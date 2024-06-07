package br.com.coralink.api.dto;

import br.com.coralink.api.model.Bairro;
import org.springframework.hateoas.Link;

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
