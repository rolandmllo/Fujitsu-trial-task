package app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class WindSpeedExtraFee extends BaseEntity{
    private Double windSpeedExtraFee;

    private Double lowerWindSpeed;
    private Double higherWindSpeed;

    private Boolean forbidden = false;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
