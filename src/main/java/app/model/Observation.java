package app.model;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import app.services.helper.TimestampXMLAdapter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Observation {
    @XmlAttribute
    @XmlJavaTypeAdapter(TimestampXMLAdapter.class)
    private LocalDateTime timestamp;

    @XmlElement(name = "station")
    private List<Weather> stations;

}


