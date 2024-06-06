package br.com.coralink.api.dto;

import br.com.coralink.api.model.Sensor;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SensorResponseDTO(
        Long id,

        String nome,

        String numeroSerie,

        int codigoPareamento,

        String sensorMain,

        int latitude,

        int longitude,

        Long empresaId,

        Long usuarioId,

        Link link
) {
    public SensorResponseDTO(Sensor sensor) {
        this(sensor.getId(), sensor.getNome(), sensor.getNumeroSerie(), sensor.getCodigoPareamento(), sensor.getSensorMain(), sensor.getLatitude(), sensor.getLongitude(), sensor.getEmpresa().getId(), sensor.getUsuario().getId(), null);
    }
}
