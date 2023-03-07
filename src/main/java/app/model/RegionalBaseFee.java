package app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class RegionalBaseFee extends BaseEntity{

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "city_id")
    City city;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    Double regionalBaseFee;

}
