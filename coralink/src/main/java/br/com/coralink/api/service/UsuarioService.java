package br.com.coralink.api.service;

import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.dto.*;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.Usuario;
import br.com.coralink.api.dto.LoginDTO;
import br.com.coralink.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsuarioService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponseDTO> buscarUsuarios() {
        return usuarioRepository.findAll(paginacaoPersonalizada).map(usuario -> toDTO(usuario, true));
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(usuario -> toDTO(usuario, false)).orElse(null);
    }

    public UsuarioResponseDTO salvarUsuario(UsuarioDTO novoUsuario){
        verificarSenhaConfi(novoUsuario.senhaConfirmacao(), novoUsuario.senha());

        boolean existente = this.usuarioRepository.existsByEmail(novoUsuario.email());

        if (existente){
            throw new ErroNegocioException("Telefone já existente");
        }

        Usuario usuario = new Usuario(novoUsuario);

        usuario = this.usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }
    public void verificarSenhaConfi(String senhaConfi, String senha) {
        if (!senhaConfi.equals(senha)) {
            throw new ErroNegocioException("Senha de confirmação está diferente");
        }
    }

    public UsuarioResponseDTO atualizarSenha(Long id, NovaSenhaDTO senhaDTO){

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailAndSenha(senhaDTO.email().toLowerCase(),  senhaDTO.senhaAtual());
        if (usuarioOptional.isPresent()) {

            if(!Objects.equals(id, usuarioOptional.get().getId())){
                throw new ErroNegocioException("Erro de incompatibilidade de dados");
            }

            if (!senhaDTO.senhaNova().equals(senhaDTO.senhaConfirmacao())) {
                throw new ErroNegocioException("A nova senha não foi confirmada corretamente");
            }

            if (senhaDTO.senhaNova().equals(senhaDTO.senhaAtual())) {
                throw new ErroNegocioException("A nova senha não pode ser igual a anterior");
            }

            Usuario usuario = usuarioOptional.get();

            usuario.setSenha(senhaDTO.senhaNova());

            usuarioRepository.save(usuario);

            return new UsuarioResponseDTO(usuario);
        }
        throw new ErroNegocioException("usuario não encontrado");
    }

    public UsuarioResponseDTO logar(LoginDTO login) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(login.email(), login.senha());
        if(usuario.isEmpty()) {
            throw new ErroNegocioException("Usuario não encontrado");
        }

        if (!usuario.get().getSenha().equals(login.senha())) {
            throw new ErroNegocioException("Senha Incorreta");
        }

        return new UsuarioResponseDTO(usuario.get());
    }

private UsuarioResponseDTO toDTO(Usuario usuario, boolean self) {
    Link link;
    if (self) {
        link = linkTo(methodOn(EmpresaController.class).buscarEmpresaPorId(usuario.getId())).withSelfRel();
    } else {
        link = linkTo(methodOn(EmpresaController.class).buscarEmpresas()).withRel("Lista de Usuarios");
    }
    return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),

            link
    );
}

}