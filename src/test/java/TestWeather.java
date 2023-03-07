import model.Weather;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import services.WeatherService;
import java.io.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;




public class TestWeather {


    @Test
    public void GetWeatherDataFromXML() {

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
}