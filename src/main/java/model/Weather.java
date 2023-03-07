package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import java.sql.Timestamp;


@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
    @Id @GeneratedValue
    private Long id;


    private String name;
    private Integer wmocode;

    @XmlElement(name = "airtemperature")
    private Double airTemperature;
    @XmlElement(name = "windspeed")

    private Double windSpeed;

    private String phenomenon;

    private Timestamp timestamp;

}
