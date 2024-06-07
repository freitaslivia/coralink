package br.com.coralink.api.dto;

import br.com.coralink.api.model.Sensor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record SensorDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 12, message = "O campo pode ter no máximo 12 caracteres")
        @Schema(description = "Nome do sensor", example = "Sensor123")
        String nome,


        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 23, message = "O campo pode ter no máximo 23 caracteres")
        @Pattern(regexp = "^[0-9]{1,23}$", message = "Deve ter somente números")
        @Schema(description = "Número de Série", example = "12345678901234567890123")
        String numeroSerie,

        @NotNull(message = "O campo não pode ser nulo")
        @Digits(integer=10, message = "Somente 10 digitos", fraction = 0)
        @Schema(description = "Código de Pareamento", example = "123")
        int codigoPareamento,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 17, message = "O campo pode ter no máximo 17 caracteres")
        @Schema(description = "Sensor Main", example = "SensorMain12345")
        String sensorMain,

        @NotNull(message = "O campo não pode ser nulo")
        @Digits(integer=6, message = "Maximo 6 digitos", fraction = 0)
        @Schema(description = "Latitude", example = "238888")
        int latitude,

        @NotNull(message = "O campo não pode ser nulo")
        @Digits(integer=6, message = "Maximo 6 digitos", fraction = 0)
        @Schema(description = "Longitude", example = "238888")
        int longitude,

        @Schema(description = "ID usuario", example = "1")
        Long empresaId,

        @Schema(description = "ID usuario", example = "1")
        Long usuarioId
) {
    public SensorDTO(Sensor sensor) {
        this(sensor.getId(), sensor.getNome(), sensor.getNumeroSerie(), sensor.getCodigoPareamento(), sensor.getSensorMain(), sensor.getLatitude(), sensor.getLongitude(), sensor.getEmpresa().getId(), sensor.getUsuario().getId());
    }
}
