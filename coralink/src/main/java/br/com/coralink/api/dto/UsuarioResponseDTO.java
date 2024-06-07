package br.com.coralink.api.dto;

import br.com.coralink.api.model.Usuario;
import org.springframework.hateoas.Link;

import java.util.Optional;

public record UsuarioResponseDTO(

        Long id,

        String nome,

        String email,

        Link link
) {

        public UsuarioResponseDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getNome(), usuario.getEmail(), null);
        }
}