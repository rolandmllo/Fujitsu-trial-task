package app.services;

import app.Dao.CityRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.Weather;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import app.model.Observation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * The type Weather update service from URL and with CRONJOB support.
 */
@Service
public class weatherUpdateService {
    private static final String WEATHER_API_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    /**
     * Instantiates a new Weather update service.
     *
     * @param weatherRepository the weather repository
     * @param cityRepository    the city repository
     */
    public weatherUpdateService(WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * On startup loads weather data from API.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        // updateWeatherDataFromFile(); // For testing
        updateWeatherData();
    }

    @Scheduled(cron="0 15 0 * * ?")
    private void onSchedule() {
        updateWeatherData();
    }

    private void updateWeatherDataFromFile(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("observationData.xml");
        Observation observationData = parseXML(is);
        persistRelatedWeatherData(observationData);
        System.out.println("Weather updated");
    }


    private void updateWeatherData(){
        Observation observationData = parseXMLFromUrl(WEATHER_API_URL);
        persistRelatedWeatherData(observationData);
        System.out.println("Weather updated");
    }
    private void persistRelatedWeatherData(Observation observationData){
        List<City> cityList = cityRepository.findAll();

        for (Weather station : observationData.getStations()) {
            for (City city : cityList) {
                if (station.getObservationStationName().equals(city.getWeatherObservationStation())){
                    station.setTimestamp(observationData.getTimestamp());
                    weatherRepository.save(station);
                }
            }
        }
    }


    /**
     * Parse xml from url to Observation model.
     *
     * @param inputUrl the input url
     * @return the observation
     */
    public Observation parseXMLFromUrl(String inputUrl) {

        try (InputStream input = new URL(inputUrl).openStream()){
            return parseXML(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Parse xml to observation from InputStream.
     *
     * @param xmlInput the xml input
     * @return the observation
     */
    public Observation parseXML(InputStream xmlInput) {

        Observation observations;

        try {
            JAXBContext context = JAXBContext.newInstance(Observation.class);
            observations = (Observation) context
                    .createUnmarshaller()
                    .unmarshal(xmlInput);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return observations;
    }
}
