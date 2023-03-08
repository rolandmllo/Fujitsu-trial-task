package app.model;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import app.services.helper.TimestampXMLAdapter;
import java.sql.Timestamp;
import java.util.List;

@Data
@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Observation {
    @XmlAttribute
    @XmlJavaTypeAdapter(type = String.class, value = TimestampXMLAdapter.class)
    private Timestamp timestamp;

    @XmlElement(name = "station")
    private List<Weather> stations;

}


