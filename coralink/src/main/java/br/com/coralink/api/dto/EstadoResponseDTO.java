package br.com.coralink.api.dto;

import br.com.coralink.api.model.Estado;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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