package br.com.coralink.api.dto;

import br.com.coralink.api.model.Sensor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record SensorAtualizarDTO(
        @NotNull(message = "O campo não pode ser nulo")
        @Digits(integer=6, message = "Maximo 6 digitos", fraction = 0)
        @Schema(description = "Latitude", example = "238888")
        int latitude,

        @NotNull(message = "O campo não pode ser nulo")
        @Digits(integer=6, message = "Maximo 6 digitos", fraction = 0)
        @Schema(description = "Longitude", example = "238888")
        int longitude
) {
    public SensorAtualizarDTO(Sensor sensor) {
        this( sensor.getLatitude(), sensor.getLongitude());
    }
}
