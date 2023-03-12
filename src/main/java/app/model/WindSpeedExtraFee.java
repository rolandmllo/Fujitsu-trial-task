package app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class WindSpeedExtraFee extends BaseEntity{
    private Double windSpeedExtraFee;

    private Double lowerWindSpeed;
    private Double higherWindSpeed;

    private Boolean forbidden = false;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
