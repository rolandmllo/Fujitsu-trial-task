package app.model;

import jakarta.persistence.*;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import java.sql.Timestamp;


@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;
    private Integer wmocode;

    @XmlElement(name = "airtemperature")
    @Column(name = "airtemperature")
    private Double airTemperature;
    @XmlElement(name = "windspeed")
    @Column(name = "windspeed")
    private Double windSpeed;

    @Column(name = "phenomenon")
    private String phenomenon;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
