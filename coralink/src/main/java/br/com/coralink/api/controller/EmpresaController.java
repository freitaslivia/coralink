package br.com.coralink.api.controller;

import br.com.coralink.api.dto.EmpresaDTO;
import br.com.coralink.api.dto.EmpresaResponseDTO;
import br.com.coralink.api.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/empresas", produces = {"application/json"})
@Tag(name = "api-empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Retorna todas Empresas em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<EmpresaResponseDTO>> buscarEmpresas() {
        Page<EmpresaResponseDTO> empresaDTO = empresaService.buscarEmpresas();
        if (empresaDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(empresaDTO);
        }
    }


    @Operation(summary = "Retorna uma empresa específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma Empresa encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarEmpresaPorId(@PathVariable Long id) {
        EmpresaResponseDTO empresaDTO = empresaService.buscarEmpresaPorId(id);
        if (empresaDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(empresaDTO);
        }
    }


    @Operation(summary = "Grava uma Empreaa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> gravarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaResponseDTO empresa = empresaService.salvarEmpresa(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }

/*
    @Operation(summary = "Atualiza um produto com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaResponseDTO empresa = empresaService.atualizarProduto(id, empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }
 */
}