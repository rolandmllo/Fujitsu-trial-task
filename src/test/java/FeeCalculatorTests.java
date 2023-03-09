import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import app.services.RBFCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = app.Application.class)
public class FeeCalculatorTests {

    @MockBean(CityRepository.class)
    CityRepository cityRepository;
    @MockBean(VehicleRepository.class)
    VehicleRepository vehicleRepository;

    @Autowired
    RBFCalculator rbfCalculator;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRBFCalculator(){

    Vehicle vehicle = new Vehicle();
    vehicle.setVehicleType("Scooter");
    City city = new City();
    city.setCityName("Pärnu");

    PriceModel priceModel = new PriceModel();
    priceModel.setVehicle(vehicle);
    priceModel.setCity(city);

    Double rbf = rbfCalculator.setFeeRate(priceModel).getRegionalBaseFee().getRegionalBaseFee();

    assertThat(rbf).isEqualTo(2.5);
    }



}
