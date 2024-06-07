package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.controller.LogradouroController;
import br.com.coralink.api.dto.*;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.*;
import br.com.coralink.api.repository.BairroRepository;
import br.com.coralink.api.repository.EmpresaRepository;
import br.com.coralink.api.repository.LogradouroRepository;
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
public class LogradouroService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private LogradouroRepository logradouroRepository;


    @Autowired
    private BairroRepository bairroRepository;

    public Page<LogradouroResponseDTO> buscarLogradouros() {
        return logradouroRepository.findAll(paginacaoPersonalizada).map(logradouro -> toDTO(logradouro, true));
    }

    public LogradouroResponseDTO buscarLogradouroPorId(Long id) {
        return logradouroRepository.findById(id).map(logradouro -> toDTO(logradouro, false)).orElse(null);
    }

    public LogradouroResponseDTO salvarLogradouro(LogradouroDTO novoLogradouro){
        Optional<Bairro> bairro = bairroRepository.findById(novoLogradouro.idBairro());
        if (bairro.isEmpty()) {
            throw new ErroNegocioException("Bairro não encontrado");
        }
        Logradouro logradouro = new Logradouro(novoLogradouro, bairro.get());

        logradouro = this.logradouroRepository.save(logradouro);

        return new LogradouroResponseDTO(logradouro);
    }

    private LogradouroResponseDTO toDTO(Logradouro logradouro, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(LogradouroController.class).buscarLogradouroPorId(logradouro.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(LogradouroController.class).buscarLogradouros()).withRel("Lista de Logradouros");
        }
        return new LogradouroResponseDTO(
                logradouro.getId(),
                logradouro.getNome(),
                logradouro.getCep(),
                logradouro.getTipo(),
                logradouro.getBairro().getId(),
                link
        );
    }

}