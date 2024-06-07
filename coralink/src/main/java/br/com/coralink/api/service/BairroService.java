package br.com.coralink.api.service;


import br.com.coralink.api.controller.BairroController;
import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.dto.*;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.*;
import br.com.coralink.api.repository.BairroRepository;
import br.com.coralink.api.repository.CidadeRepository;
import br.com.coralink.api.repository.EmpresaRepository;
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
public class BairroService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Page<BairroResponseDTO> buscarBairros() {
        return bairroRepository.findAll(paginacaoPersonalizada).map(bairro -> toDTO(bairro, true));
    }

    public BairroResponseDTO buscarBairroPorId(Long id) {
        return bairroRepository.findById(id).map(bairro -> toDTO(bairro, false)).orElse(null);
    }

    public BairroResponseDTO salvarBairro(BairroDTO novoBairro){
        Optional<Cidade> cidade = cidadeRepository.findById(novoBairro.idCidade());
        if (cidade.isEmpty()) {
            throw new ErroNegocioException("Cidade não encontrada");
        }

        Bairro bairroExistente = this.bairroRepository.findByNomeAndIdCidade(novoBairro.nome().toLowerCase(), novoBairro.idCidade());

        if(bairroExistente != null){
            return new BairroResponseDTO(bairroExistente);
        }
        Bairro bairro = new Bairro(novoBairro, cidade.get());

        bairro = this.bairroRepository.save(bairro);

        return new BairroResponseDTO(bairro);
    }

    private BairroResponseDTO toDTO(Bairro bairro, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(BairroController.class).buscarBairroPorId(bairro.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(BairroController.class).buscarBairros()).withRel("Lista de Bairros");
        }
        return new BairroResponseDTO(
                bairro.getId(),
                bairro.getNome(),
                bairro.getNomeZona(),
                bairro.getCidade().getId(),
                link
        );
    }

}