package br.com.coralink.api.dto;

import br.com.coralink.api.model.Estado;
import org.springframework.hateoas.Link;

public record EstadoResponseDTO(
        Long id,

        String nome,

        String sigla,

        Link link
) {

    public EstadoResponseDTO(Estado estado) {
            this(estado.getId(), estado.getSigla(), estado.getNome(), null);
        }
    }