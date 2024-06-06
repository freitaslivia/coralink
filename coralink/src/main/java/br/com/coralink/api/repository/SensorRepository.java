package br.com.coralink.api.repository;

import br.com.coralink.api.model.Sensor;
import br.com.coralink.api.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
