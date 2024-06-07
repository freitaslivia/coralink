package br.com.coralink.api.controller;

import br.com.coralink.api.dto.BairroDTO;
import br.com.coralink.api.dto.BairroResponseDTO;
import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.service.BairroService;
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
@RequestMapping(value = "/bairros", produces = {"application/json"})
@Tag(name = "api-bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @Operation(summary = "Retorna todos Bairros em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum bairro encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<BairroResponseDTO>> buscarBairros() {
        Page<BairroResponseDTO> bairroDTO = bairroService.buscarBairros();
        if (bairroDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(bairroDTO);
        }
    }


    @Operation(summary = "Retorna uma empresa específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Bairro encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<BairroResponseDTO> buscarBairroPorId(@PathVariable Long id) {
        BairroResponseDTO bairroDTO = bairroService.buscarBairroPorId(id);
        if (bairroDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(bairroDTO);
        }
    }


    @Operation(summary = "Grava uma Bairro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bairro gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<BairroResponseDTO> gravarBairro(@Valid @RequestBody BairroDTO bairroDTO) {
        BairroResponseDTO bairro = bairroService.salvarBairro(bairroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bairro);
    }

}