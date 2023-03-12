package app.model;
import jakarta.persistence.*;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather extends BaseEntity{

    @XmlElement(name = "name")
    private String observationStationName;
    private Integer wmocode;

    @XmlElement(name = "airtemperature")
    private Double airTemperature;
    @XmlElement(name = "windspeed")
    private Double windSpeed;

    private String phenomenon;

    private LocalDateTime timestamp;

}
