package br.com.coralink.api.dto;

import br.com.coralink.api.model.Logradouro;
import org.springframework.hateoas.Link;

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
