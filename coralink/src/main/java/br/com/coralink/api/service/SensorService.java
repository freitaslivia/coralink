package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.repository.EmpresaRepository;
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
public class SensorService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private EmpresaRepository empresaRepository;

    public Page<EmpresaResponseDTO> buscarEmpresas() {
        return empresaRepository.findAll(paginacaoPersonalizada).map(empresa -> toDTO(empresa, true));
    }

    public EmpresaResponseDTO buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).map(empresa -> toDTO(empresa, false)).orElse(null);
    }

    public EmpresaResponseDTO salvarEmpresa(EmpresaDTO novaEmpresa){
        Empresa empresa = new Empresa(novaEmpresa);

        empresa = this.empresaRepository.save(empresa);

        return new EmpresaResponseDTO(empresa);
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


    private EmpresaResponseDTO toDTO(Empresa empresa, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(EmpresaController.class).buscarEmpresaPorId(empresa.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(EmpresaController.class).buscarEmpresas()).withRel("Lista de Empresas");
        }
        return new EmpresaResponseDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getNomeExibicao(),
                empresa.getEmail(),
                empresa.getTpEmpresa(),
                empresa.getTelefone(),
                link
        );
    }

}