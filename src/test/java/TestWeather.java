import app.model.Weather;
import org.junit.jupiter.api.Test;
import app.services.WeatherService;
import java.io.*;
import static org.assertj.core.api.Assertions.assertThat;


public class TestWeather {


    @Test
    public void GetWeatherDataFromXMLFile() {

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
    public void GetWeatherDataFromUrl() {

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
    public void AddWeatherDataToDatabase(){
        WeatherService ws = new WeatherService();
        Weather weather = new Weather();
        weather.setName("testest");

        ws.addWeatherToDatabase(weather);

    }
}