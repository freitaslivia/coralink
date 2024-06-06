package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.controller.TecnicoController;
import br.com.coralink.api.dto.TecnicoDTO;
import br.com.coralink.api.dto.TecnicoResponseDTO;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.Tecnico;
import br.com.coralink.api.model.Usuario;
import br.com.coralink.api.repository.TecnicoRepository;
import br.com.coralink.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TecnicoService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<TecnicoResponseDTO> buscarTecnicos() {
        return tecnicoRepository.findAll(paginacaoPersonalizada).map(tecnico -> toDTO(tecnico, true));
    }

    public TecnicoResponseDTO buscarTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id).map(tecnico -> toDTO(tecnico, false)).orElse(null);
    }

    public TecnicoResponseDTO salvarTecnico(TecnicoDTO novoTecnico){
        Optional<Usuario> usuario = usuarioRepository.findById(novoTecnico.idUsuario());
        if (usuario == null) {
            throw new ErroNegocioException("Usuário não encontrado");
        }
        Tecnico tecnico = new Tecnico(novoTecnico, usuario);

        tecnico = this.tecnicoRepository.save(tecnico);

        return new TecnicoResponseDTO(tecnico);
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


    private TecnicoResponseDTO toDTO(Tecnico tecnico, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(TecnicoController.class).buscarTecnicoPorId(tecnico.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(TecnicoController.class).buscarTecnicos()).withRel("Lista de Tecnicos");
        }
        return new TecnicoResponseDTO(
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getNomeEmpresa(),
                tecnico.getUsuario(),
                link
        );
    }

}