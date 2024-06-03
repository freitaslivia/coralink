package br.com.coralink.api.dto;

import br.com.coralink.api.model.Tecnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TecnicoResponseDTO(

        Long id,

        String nome,

        String nomeEmpresa

) {

        public TecnicoResponseDTO(Tecnico tecnico) {
                this(tecnico.getId(), tecnico.getNome(), tecnico.getNomeEmpresa());
        }
}