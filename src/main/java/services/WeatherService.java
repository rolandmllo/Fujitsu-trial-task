package services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import model.Observation;
import java.io.InputStream;


public class WeatherService {

    public Observation ParseXML(InputStream xmlInput) {

        Observation observations;

        try {
            JAXBContext context = JAXBContext.newInstance(Observation.class);
            observations = (Observation) context.createUnmarshaller()
                    .unmarshal(xmlInput);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return observations;
    }

}
