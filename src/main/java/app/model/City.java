package app.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class City extends BaseEntity{

    private String cityName;
    private String weatherObservationStation;


}
