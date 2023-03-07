package app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class WeatherPhenomenonExtraFee extends BaseEntity {
    private Double weatherPhenomenonExtraFee;

    private String weatherPhenomenon;

    private Boolean forbidden = false;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
