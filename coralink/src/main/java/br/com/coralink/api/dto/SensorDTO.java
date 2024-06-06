package br.com.coralink.api.dto;

import br.com.coralink.api.model.Cidade;
import br.com.coralink.api.model.Estado;
import br.com.coralink.api.model.Sensor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SensorDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 12, message = "O campo pode ter no máximo 12 caracteres")
        @Schema(description = "Nome do sensor", example = "Sensor123")
        String nome,


        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 23, message = "O campo pode ter no máximo 23 caracteres")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        @Schema(description = "Número de Série", example = "12345678901234567890123")
        String numeroSerie,

        @NotNull(message = "O campo não pode ser nulo")
        @Size(max = 10, message = "O campo pode ter no máximo 10 digitos")
        @Schema(description = "Código de Pareamento", example = "123")
        int codigoPareamento,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 17, message = "O campo pode ter no máximo 17 caracteres")
        @Schema(description = "Sensor Main", example = "SensorMain12345")
        String sensorMain,

        @NotNull(message = "O campo não pode ser nulo")
        @Size(max = 6, message = "O campo pode ter no máximo 6 caracteres")
        @Schema(description = "Latitude", example = "-23.55052")
        int latitude,

        @NotNull(message = "O campo não pode ser nulo")
        @Size(max = 6, message = "O campo pode ter no máximo 6 caracteres")
        @Schema(description = "Longitude", example = "-46.633308")
        int longitude,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        @Schema(description = "ID usuario", example = "1")
        Long empresaId,

        @NotNull(message = "O campo não pode ser nulo")
        @Pattern(regexp = "^[0-9]$", message = "Deve ter somente números")
        @Schema(description = "ID usuario", example = "1")
        Long usuarioId
) {
    public SensorDTO(Sensor sensor) {
        this(sensor.getId(), sensor.getNome(), sensor.getNumeroSerie(), sensor.getCodigoPareamento(), sensor.getSensorMain(), sensor.getLatitude(), sensor.getLongitude(), sensor.getEmpresa().getId(), sensor.getUsuario().getId());
    }
}
