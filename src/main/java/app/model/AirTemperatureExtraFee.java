package app.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class AirTemperatureExtraFee extends BaseEntity{

    private Double airTemperatureExtraFee;

    private Double lowerTemp;
    private Double higherTemp;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}
