package app.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class City extends BaseEntity{

    private String cityName;
    private String weatherObservationStation;


}
