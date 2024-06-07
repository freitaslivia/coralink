package br.com.coralink.api.dto;

import br.com.coralink.api.model.Sensor;
import org.springframework.hateoas.Link;

public record SensorResponseDTO(
        Long id,

        String nome,

        String numeroSerie,

        String sensorMain,

        int latitude,

        int longitude,

        Long empresaId,

        Long usuarioId,

        Link link
) {
    public SensorResponseDTO(Sensor sensor) {
        this(sensor.getId(), sensor.getNome(), sensor.getNumeroSerie(), sensor.getSensorMain(), sensor.getLatitude(), sensor.getLongitude(), sensor.getEmpresa().getId(), sensor.getUsuario().getId(), null);
    }
}
