package br.com.coralink.api.service;


import br.com.coralink.api.controller.CidadeController;
import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.dto.*;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.*;
import br.com.coralink.api.repository.CidadeRepository;
import br.com.coralink.api.repository.EmpresaRepository;
import br.com.coralink.api.repository.EstadoRepository;
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
public class CidadeService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Page<CidadeResponseDTO> buscarCidades() {
        return cidadeRepository.findAll(paginacaoPersonalizada).map(cidade -> toDTO(cidade, true));
    }

    public CidadeResponseDTO buscarCidadePorId(Long id) {
        return cidadeRepository.findById(id).map(cidade -> toDTO(cidade, false)).orElse(null);
    }

    public CidadeResponseDTO salvarCidade(CidadeDTO novaCidade){

        Optional<Estado> estado = estadoRepository.findById(novaCidade.idEstado());
        if (estado.isEmpty()) {
            throw new ErroNegocioException("Estado não encontrado");
        }
        Cidade cidadeExistente = this.cidadeRepository.findByNomeAndIdEstado(novaCidade.nome().toLowerCase(), novaCidade.idEstado());

        if(cidadeExistente != null){
            return new CidadeResponseDTO(cidadeExistente);
        }

        Cidade cidade = new Cidade(novaCidade, estado.get());

        cidade = this.cidadeRepository.save(cidade);

        return new CidadeResponseDTO(cidade);
    }

    private CidadeResponseDTO toDTO(Cidade cidade, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(CidadeController.class).buscarCidadePorId(cidade.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(CidadeController.class).buscarCidades()).withRel("Lista de Cidades");
        }
        return new CidadeResponseDTO(
                cidade.getId(),
                cidade.getNome(),
                cidade.getDdd(),
                cidade.getEstado().getId(),
                link
        );
    }

}