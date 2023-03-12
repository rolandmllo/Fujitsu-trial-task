package app.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AirTemperatureExtraFee extends BaseEntity{


    private Double airTemperatureExtraFee;

    private Double lowerTemp;
    private Double higherTemp;

    private Boolean forbidden = false;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}
