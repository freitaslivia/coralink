package br.com.coralink.api.controller;

import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.dto.EstadoDTO;
import br.com.coralink.api.dto.EstadoResponseDTO;
import br.com.coralink.api.service.EmpresaService;
import br.com.coralink.api.service.EstadoService;
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
@RequestMapping(value = "/estados", produces = {"application/json"})
@Tag(name = "api-estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Retorna todos Estados em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum estado encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<EstadoResponseDTO>> buscarEstados() {
        Page<EstadoResponseDTO> estadoDTO = estadoService.buscarEstados();
        if (estadoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(estadoDTO);
        }
    }


    @Operation(summary = "Retorna um estado específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum estado encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> buscarEstadoPorId(@PathVariable Long id) {
        EstadoResponseDTO estadoDTO = estadoService.buscarEstadoPorId(id);
        if (estadoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(estadoDTO);
        }
    }

    @Operation(summary = "Grava um Estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estado gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<EstadoResponseDTO> gravarEstado(@Valid @RequestBody EstadoDTO estadoDTO) {
        EstadoResponseDTO estado = estadoService.salvarEstado(estadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }
}