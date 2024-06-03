package br.com.coralink.api.dto;

import br.com.coralink.api.model.Estado;
import jakarta.validation.constraints.*;

public record EstadoDTO(

        Long id,

        @NotNull(message = "O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 30, message = "O campo pode ter no máximo 30 caracteres")
        String nome,

        @NotNull(message = "O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[A-Z]{2}$", message = "A sigla deve ter exatamente 2 letras maiúsculas")
        String sigla
) {

    public EstadoDTO(Estado estado) {
            this(estado.getId(), estado.getSigla(), estado.getNome());
        }
    }