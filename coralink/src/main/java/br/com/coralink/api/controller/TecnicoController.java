package br.com.coralink.api.controller;

import br.com.coralink.api.dto.*;
import br.com.coralink.api.model.Tecnico;
import br.com.coralink.api.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/tecnicos", produces = {"application/json"})
@Tag(name = "api-tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @Operation(summary = "Retorna todos Tecnicos em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Tecnico encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<TecnicoResponseDTO>> buscarTecnicos() {
        Page<TecnicoResponseDTO> tecnicoDTO = tecnicoService.buscarTecnicos();
        if (tecnicoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(tecnicoDTO);
        }
    }


    @Operation(summary = "Retorna um tecnico específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum tecnico encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> buscarTecnicoPorId(@PathVariable Long id) {
        TecnicoResponseDTO tecnicoDTO = tecnicoService.buscarTecnicoPorId(id);
        if (tecnicoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(tecnicoDTO);
        }
    }


    @Operation(summary = "Grava um Tecnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tecnico gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<TecnicoResponseDTO> gravarTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoResponseDTO tecnico = tecnicoService.salvarTecnico(tecnicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnico);
    }


    @Operation(summary = "Atualiza um tecnico com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tecnico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> atualizarTecnico(@PathVariable Long id, @Valid @RequestBody TecnicoAtualizarDTO tecnicoDTO) {
        TecnicoResponseDTO tecnico = tecnicoService.atualizarTecnico(id, tecnicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnico);
    }
}