import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import app.services.RBFCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = app.Application.class)
public class FeeCalculatorUnitTests {

@Test
    public void testRBFCalculator(){

    Vehicle vehicle = new Vehicle();
    vehicle.setVehicleType("Scooter");
    City city = new City();
    city.setCityName("Pärnu");

    PriceModel priceModel = new PriceModel();
    priceModel.setVehicle(vehicle);
    priceModel.setCity(city);

    RBFCalculator rbfCalculator = new RBFCalculator();
    Double rbf = rbfCalculator.CalculateRBF(priceModel).getRegionalBaseFee().getRegionalBaseFee();

    assertThat(rbf).isEqualTo(2.5);
    }



}
