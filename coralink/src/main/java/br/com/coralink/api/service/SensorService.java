package br.com.coralink.api.service;


import br.com.coralink.api.controller.EmpresaController;
import br.com.coralink.api.controller.SensorController;
import br.com.coralink.api.dto.*;
import br.com.coralink.api.exception.ErroNegocioException;
import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Sensor;
import br.com.coralink.api.model.Tecnico;
import br.com.coralink.api.model.Usuario;
import br.com.coralink.api.repository.EmpresaRepository;
import br.com.coralink.api.repository.SensorRepository;
import br.com.coralink.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SensorService {
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nome").ascending());

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Page<SensorResponseDTO> buscarSensores() {
        return sensorRepository.findAll(paginacaoPersonalizada).map(sensor -> toDTO(sensor, true));
    }

    public SensorResponseDTO buscarSensorPorId(Long id) {
        return sensorRepository.findById(id).map(sensor -> toDTO(sensor, false)).orElse(null);
    }

    public SensorResponseDTO salvarSensor(SensorDTO novoSensor){
        Optional<Usuario> usuario = usuarioRepository.findById(novoSensor.usuarioId());
        if (usuario.isEmpty()) {
            throw new ErroNegocioException("Usuário não encontrado");
        }
        Optional<Empresa> empresa = empresaRepository.findById(novoSensor.empresaId());
        if (empresa.isEmpty()) {
            throw new ErroNegocioException("Empresa não encontrado");
        }

        boolean existente = this.sensorRepository.existsByNumeroSerie(novoSensor.numeroSerie());

        if (existente){
            throw new ErroNegocioException("Numero de serie já existente");
        }

        Sensor sensor = new Sensor(novoSensor, usuario.get(), empresa.get());

        sensor = this.sensorRepository.save(sensor);

        return new SensorResponseDTO(sensor);
    }

    public void deletarSensor(Long id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        sensorOptional.ifPresent(sensorRepository::delete);
    }

    public SensorResponseDTO atualizarSensor(Long id, @Valid SensorAtualizarDTO sensorDTO){
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        if (sensorOptional.isPresent()) {

            Sensor sensor = sensorOptional.get();
            sensor.setLatitude(sensorDTO.latitude());
            sensor.setLongitude(sensorDTO.longitude());

            sensorRepository.save(sensor);

            return new SensorResponseDTO(sensor);
        }
        return null;
    }


    private SensorResponseDTO toDTO(Sensor sensor, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(SensorController.class).buscarSensorPorId(sensor.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(SensorController.class).buscarSensores()).withRel("Lista de Sensores");
        }
        return new SensorResponseDTO(
                sensor.getId(),
                sensor.getNome(),
                sensor.getNumeroSerie(),
                sensor.getSensorMain(),
                sensor.getLatitude(),
                sensor.getLongitude(),
                sensor.getEmpresa().getId(),
                sensor.getUsuario().getId(),
                link
        );
    }
}