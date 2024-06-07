package br.com.coralink.api.controller;

import br.com.coralink.api.dto.*;
import br.com.coralink.api.dto.LoginDTO;
import br.com.coralink.api.service.UsuarioService;
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
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Retorna todos Usuarios em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Usuario encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarUsuarios() {
        Page<UsuarioResponseDTO> usuarioDTO = usuarioService.buscarUsuarios();
        if (usuarioDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(usuarioDTO);
        }
    }


    @Operation(summary = "Retorna um usuario específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Usuario encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        if (usuarioDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(usuarioDTO);
        }
    }


    @Operation(summary = "Grava um Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> gravarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioResponseDTO usuario = usuarioService.salvarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @Operation(summary = "Atualiza um usuario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarTecnico(@PathVariable Long id, @Valid @RequestBody NovaSenhaDTO senhaDTO) {
        UsuarioResponseDTO senha = usuarioService.atualizarSenha(id, senhaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(senha);
    }

    @Operation(summary = "Login de um Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login feito com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping("login")
    public ResponseEntity<UsuarioResponseDTO> login(@Valid @RequestBody LoginDTO login) {

        UsuarioResponseDTO usuario = this.usuarioService.logar(login);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}