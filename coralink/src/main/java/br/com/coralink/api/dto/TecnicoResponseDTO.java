package br.com.coralink.api.dto;

import br.com.coralink.api.model.Tecnico;
import io.swagger.v3.oas.annotations.links.Link;

public record TecnicoResponseDTO(

        Long id,

        String nome,

        String nomeEmpresa,

        Link link

) {

        public TecnicoResponseDTO(Tecnico tecnico) {
                this(tecnico.getId(), tecnico.getNome(), tecnico.getNomeEmpresa(), null);
        }
}