package br.com.coralink.api.controller;

import br.com.coralink.api.dto.*;
import br.com.coralink.api.service.SensorService;
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
@RequestMapping(value = "/sensores", produces = {"application/json"})
@Tag(name = "api-sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Operation(summary = "Retorna todos Sensores em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Sensor encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<SensorResponseDTO>> buscarSensores() {
        Page<SensorResponseDTO> sensorDTO = sensorService.buscarSensores();
        if (sensorDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(sensorDTO);
        }
    }


    @Operation(summary = "Retorna um Sensor específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum sensor encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> buscarSensorPorId(@PathVariable Long id) {
        SensorResponseDTO sensorDTO = sensorService.buscarSensorPorId(id);
        if (sensorDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(sensorDTO);
        }
    }


    @Operation(summary = "Grava um Sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<SensorResponseDTO> gravarTecnico(@Valid @RequestBody SensorDTO sensorDTO) {
        SensorResponseDTO sensor = sensorService.salvarSensor(sensorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensor);
    }
    @Operation(summary = "Atualiza um sensor com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })

    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> atualizarTecnico(@PathVariable Long id, @Valid @RequestBody SensorAtualizarDTO sensorDTO) {
        SensorResponseDTO sensor = sensorService.atualizarSensor(id, sensorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensor);
    }


    @Operation(summary = "Exclui um sensor com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sensor excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSensor(@PathVariable Long id) {
        sensorService.deletarSensor(id);
        return ResponseEntity.noContent().build();
    }

}