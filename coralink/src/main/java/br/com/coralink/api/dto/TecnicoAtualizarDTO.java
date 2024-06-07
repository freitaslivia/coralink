package br.com.coralink.api.dto;

import br.com.coralink.api.model.Tecnico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TecnicoAtualizarDTO(

        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{13}$", message = "Deve ter somente números e extamente 13 digitos")
        @Schema(description = "Telefone", example = "5511922445667")
        String telefone

) {

        public TecnicoAtualizarDTO(Tecnico tecnico) {
                this(tecnico.getTelefone());
        }
}