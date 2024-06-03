package br.com.coralink.api.dto;

import br.com.coralink.api.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.Link;

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