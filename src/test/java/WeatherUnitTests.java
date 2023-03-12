import app.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = app.Application.class)
public class WeatherUnitTests {
    @Autowired
    app.services.weatherUpdateService weatherUpdateService;


    @Test
    public void shouldGetWeatherDataFromXMLFile() {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("observationData.xml");

        var result = weatherUpdateService.parseXML(is);

        assertThat(result.getStations())
                .extracting(Weather::getObservationStationName)
                .anyMatch(s -> s.matches("Tallinn-Harku"))
                .anyMatch(s -> s.matches("Tartu-Tõravere"))
                .anyMatch(s -> s.matches("Pärnu"));
    }

    @Test
    public void shouldGetWeatherDataFromUrl() {

        String URL = "https://ilmateenistus.ee/ilma_andmed/xml/observations.php";

        var result = weatherUpdateService.parseXMLFromUrl(URL);

        assertThat(result.getStations())
                .extracting(Weather::getObservationStationName)
                .anyMatch(s -> s.matches("Tallinn-Harku"))
                .anyMatch(s -> s.matches("Tartu-Tõravere"))
                .anyMatch(s -> s.matches("Pärnu"));
    }


    @Test
    public void shouldAddWeatherDataToDatabase(){
        Weather weather = new Weather();
        weather.setObservationStationName("testest");

    }

}