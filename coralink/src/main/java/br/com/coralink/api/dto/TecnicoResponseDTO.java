package br.com.coralink.api.dto;

import br.com.coralink.api.model.Tecnico;
import org.springframework.hateoas.Link;

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