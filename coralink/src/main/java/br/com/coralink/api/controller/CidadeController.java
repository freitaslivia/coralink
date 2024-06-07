package br.com.coralink.api.controller;

import br.com.coralink.api.dto.CidadeDTO;
import br.com.coralink.api.dto.CidadeResponseDTO;
import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.service.CidadeService;
import br.com.coralink.api.service.EmpresaService;
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
@RequestMapping(value = "/cidades", produces = {"application/json"})
@Tag(name = "api-cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Operation(summary = "Retorna todas Cidades em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma cidade encontrada", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<CidadeResponseDTO>> buscarCidades() {
        Page<CidadeResponseDTO> cidadeDTO = cidadeService.buscarCidades();
        if (cidadeDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(cidadeDTO);
        }
    }


    @Operation(summary = "Retorna uma cidade específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma Cidade encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> buscarCidadePorId(@PathVariable Long id) {
        CidadeResponseDTO cidadeDTO = cidadeService.buscarCidadePorId(id);
        if (cidadeDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(cidadeDTO);
        }
    }


    @Operation(summary = "Grava uma Cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cidade gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<CidadeResponseDTO> gravarEmpresa(@Valid @RequestBody CidadeDTO cidadeDTO) {
        CidadeResponseDTO cidade = cidadeService.salvarCidade(cidadeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

}