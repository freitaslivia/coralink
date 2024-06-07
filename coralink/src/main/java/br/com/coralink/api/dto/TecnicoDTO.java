package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Tecnico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record TecnicoDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome", example = "Teste")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{13}$", message = "Deve ter somente números e extamente 13 digitos")
        @Schema(description = "Telefone", example = "5511922445667")
        String telefone,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome da empresa", example = "Empresa X")
        String nomeEmpresa,

        @NotNull(message = "O campo não pode ser nulo")
        @Schema(description = "ID usuario", example = "1")
        Long idUsuario

) {

        public TecnicoDTO(Tecnico tecnico) {
                this(tecnico.getId(), tecnico.getNome(), tecnico.getTelefone(), tecnico.getNomeEmpresa(), tecnico.getUsuario().getId());
        }
}