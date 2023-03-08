package app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class WeatherPhenomenonExtraFee extends BaseEntity {
    private Double weatherPhenomenonExtraFee;

    private String weatherPhenomenon;

    private Boolean forbidden = false;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
