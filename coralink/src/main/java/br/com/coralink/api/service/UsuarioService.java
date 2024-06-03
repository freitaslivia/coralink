package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.dto.UsuarioDTO;
import br.com.coralink.api.dto.UsuarioResponseDTO;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Usuario;
import br.com.coralink.api.repository.EmpresaRepository;
import br.com.coralink.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

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
        Usuario usuario = new Usuario(novoUsuario);

        usuario = this.usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }
    public void verificarSenhaConfi(String senhaConfi, String senha) {
        if (!senhaConfi.equals(senha)) {
            throw new ErroNegocioException("Senha de confirmação está diferente");
        }
    }

    /*
    public EmpresaDTO atualizarProduto(Long id, EmpresaDTO empresaDTO){
        Optional<EmpresaDTO> empresaOptional = produtoRepository.findById(id);
        if (empresaOptional.isPresent()) {

            Produto produtoAtual = produtoOptional.get();
            produtoAtual.setNome(produto.getNome());
            produtoAtual.setDescricao(produto.getDescricao());
            produtoAtual.setPreco(produto.getPreco());
            produtoAtual.setDimensoes(produto.getDimensoes());
            return produtoRepository.save(produtoAtual);
        }
        return null;
    }

     */


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