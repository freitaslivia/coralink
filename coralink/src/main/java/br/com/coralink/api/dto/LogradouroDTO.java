package br.com.coralink.api.dto;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Logradouro;
import br.com.coralink.api.model.TecnicoLogradouro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LogradouroDTO(
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nome,


        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{8}$", message = "Deve ter somente números e 8 digitos")
        String cep,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        Long idBairro,

        List<TecnicoLogradouro> tecnicoLogradouros

) {
    public LogradouroDTO(Logradouro logradouro) {
        this(logradouro.getId(), logradouro.getNome(), logradouro.getCep(), logradouro.getBairro().getId(), logradouro.getTecnicoLogradouros());
    }
}
