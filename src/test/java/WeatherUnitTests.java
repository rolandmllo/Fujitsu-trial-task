import app.model.Weather;
import org.junit.jupiter.api.Test;
import app.services.WeatherService;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = app.Application.class)
public class WeatherUnitTests {


    @Test
    public void shouldGetWeatherDataFromXMLFile() {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("observationData.xml");

        WeatherService ws = new WeatherService();
        var result = ws.ParseXML(is);

        assertThat(result.getStations())
                .extracting(Weather::getName)
                .anyMatch(s -> s.matches("Tallinn-Harku"))
                .anyMatch(s -> s.matches("Tartu-Tõravere"))
                .anyMatch(s -> s.matches("Pärnu"));
    }

    @Test
    public void shouldGetWeatherDataFromUrl() {

        String URL = "https://ilmateenistus.ee/ilma_andmed/xml/observations.php";

        WeatherService ws = new WeatherService();
        var result = ws.ParseXMLFromUrl(URL);

        assertThat(result.getStations())
                .extracting(Weather::getName)
                .anyMatch(s -> s.matches("Tallinn-Harku"))
                .anyMatch(s -> s.matches("Tartu-Tõravere"))
                .anyMatch(s -> s.matches("Pärnu"));
    }


    @Test
    public void shouldAddWeatherDataToDatabase(){
        WeatherService ws = new WeatherService();
        Weather weather = new Weather();
        weather.setName("testest");

        ws.addWeatherToDatabase(weather);

    }

}