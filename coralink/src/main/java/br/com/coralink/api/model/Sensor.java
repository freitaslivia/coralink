package br.com.coralink.api.model;

import br.com.coralink.api.dto.CidadeDTO;
import br.com.coralink.api.dto.LogradouroDTO;
import br.com.coralink.api.dto.SensorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CORALINK_SENSOR")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_sensor",
            allocationSize = 1)
    @Column(name = "pk_id_sensor",  columnDefinition = "NUMBER(10)")
    private Long id;

    @Column(name = "nm_sensor",  columnDefinition = "char(12)", nullable = false)
    private String nome;

    @Column(name = "nr_numero_serie",  columnDefinition = "char(23)", nullable = false)
    private String numeroSerie;

    @Column(name = "ds_codigo_pareamento",  columnDefinition = "NUMBER(10)", nullable = false)
    private int codigoPareamento;

    @Column(name = "ds_sensor_main",  columnDefinition = "char(17)", nullable = false)
    private String sensorMain;

    @Column(name = "nr_latitude",  columnDefinition = "NUMBER(6)", nullable = false)
    private int latitude;

    @Column(name = "nr_longitude",  columnDefinition = "NUMBER(6)", nullable = false)
    private int longitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_empresa", nullable = false)
    @JsonIgnore
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pk_id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    public Sensor(SensorDTO dadosSensor, Usuario usuario, Empresa empresa) {
        this.nome = dadosSensor.nome();
        this.numeroSerie = dadosSensor.numeroSerie();
        this.codigoPareamento = dadosSensor.codigoPareamento();
        this.sensorMain = dadosSensor.sensorMain();
        this.latitude = dadosSensor.latitude();
        this.longitude = dadosSensor.longitude();
        this.empresa = empresa;
        this.usuario = usuario;
    }
}
