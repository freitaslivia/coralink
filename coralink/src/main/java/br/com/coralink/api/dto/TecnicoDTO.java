package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Tecnico;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record TecnicoDTO(

        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{13}$", message = "Deve ter somente números e extamente 13 digitos")
        String telefone,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nomeEmpresa,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        Long idUsuario

) {

        public TecnicoDTO(Tecnico tecnico) {
                this(tecnico.getId(), tecnico.getNome(), tecnico.getTelefone(), tecnico.getNomeEmpresa(), tecnico.getUsuario().getId());
        }
}