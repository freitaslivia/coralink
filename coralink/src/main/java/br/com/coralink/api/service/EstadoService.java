package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.controller.EstadoController;
import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.dto.EstadoDTO;
import br.com.coralink.api.dto.EstadoResponseDTO;
import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Estado;
import br.com.coralink.api.repository.EmpresaRepository;
import br.com.coralink.api.repository.EstadoRepository;
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
public class EstadoService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private EstadoRepository estadoRepository;

    public Page<EstadoResponseDTO> buscarEstados() {
        return estadoRepository.findAll(paginacaoPersonalizada).map(estado -> toDTO(estado, true));
    }

    public EstadoResponseDTO buscarEstadoPorId(Long id) {
        return estadoRepository.findById(id).map(estado -> toDTO(estado, false)).orElse(null);
    }

    public EstadoResponseDTO salvarEstado(EstadoDTO novoEstado){

        Estado estadoExistente = this.estadoRepository.findBySigla(novoEstado.sigla().toUpperCase());

        if(estadoExistente != null){
            return new EstadoResponseDTO(estadoExistente);
        }


        Estado estado = new Estado(novoEstado);

        estado = this.estadoRepository.save(estado);

        return new EstadoResponseDTO(estado);
    }

    private EstadoResponseDTO toDTO(Estado estado, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(EstadoController.class).buscarEstadoPorId(estado.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(EstadoController.class).buscarEstados()).withRel("Lista de Estado");
        }
        return new EstadoResponseDTO(
                estado.getId(),
                estado.getNome(),
                estado.getSigla(),
                link
        );
    }

}