package br.com.coralink.api.controller;

import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.dto.LogradouroDTO;
import br.com.coralink.api.dto.LogradouroResponseDTO;
import br.com.coralink.api.service.EmpresaService;
import br.com.coralink.api.service.LogradouroService;
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
@RequestMapping(value = "/logradouros", produces = {"application/json"})
@Tag(name = "api-logradouro")
public class LogradouroController {

    @Autowired
    private LogradouroService logradouroService;

    @Operation(summary = "Retorna todos Logradouros em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum logradouro encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<LogradouroResponseDTO>> buscarLogradouros() {
        Page<LogradouroResponseDTO> logradouroDTO = logradouroService.buscarLogradouros();
        if (logradouroDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(logradouroDTO);
        }
    }


    @Operation(summary = "Retorna um logradouro específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum logradouro encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<LogradouroResponseDTO> buscarLogradouroPorId(@PathVariable Long id) {
        LogradouroResponseDTO logradouroDTO = logradouroService.buscarLogradouroPorId(id);
        if (logradouroDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(logradouroDTO);
        }
    }


    @Operation(summary = "Grava um Logradouro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logradouro gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<LogradouroResponseDTO> gravarLogradouro(@Valid @RequestBody LogradouroDTO logradouroDTO) {
        LogradouroResponseDTO logradouro = logradouroService.salvarLogradouro(logradouroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(logradouro);
    }
}