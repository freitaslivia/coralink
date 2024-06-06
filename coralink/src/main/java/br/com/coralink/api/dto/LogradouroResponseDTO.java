package br.com.coralink.api.dto;

import br.com.coralink.api.model.Logradouro;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LogradouroResponseDTO(
        Long id,

        String nome,

        String cep,

        String tipo,

        Long idBairro,

        Link link


) {
    public LogradouroResponseDTO(Logradouro logradouro) {
        this(logradouro.getId(), logradouro.getNome(), logradouro.getCep(), logradouro.getTipo(), logradouro.getBairro().getId(), null);
    }
}
