package app.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Vehicle extends BaseEntity{

    private String vehicleType;

}
