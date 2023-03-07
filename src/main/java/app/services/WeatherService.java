package app.services;

import app.Dao.WeatherRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import app.model.Observation;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;
    public void addWeatherToDatabase(Weather weather){
        weatherRepository.save(weather);

    }    public List<Weather> getAll(){
        return weatherRepository.findAll();

    }

    public Observation ParseXMLFromUrl(String inputUrl) {


        try (InputStream input = new URL(inputUrl).openStream()){
            return ParseXML(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



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
