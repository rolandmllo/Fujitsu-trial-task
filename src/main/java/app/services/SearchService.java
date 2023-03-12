package app.services;

import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class SearchService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public PriceModel ReadInputs(String cityInput, String vehicleInput, PriceModel priceModel){

        City city = GetCity(cityInput
                .replace("city=", "").trim());
        Vehicle vehicle = GetVehicle(vehicleInput.
                replace("vehicle=", "").trim());

        priceModel.setCity(city);
        priceModel.setVehicle(vehicle);

        return priceModel;
    }

    private Vehicle GetVehicle(String vehicleType) {
        Vehicle vehicle = vehicleRepository.findByVehicleType(vehicleType);

        if (vehicle != null) return vehicle;

        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }

    private City GetCity(String cityName){
        City city = cityRepository.findByCityName(cityName);
        if(city != null) return city;

        throw new IllegalArgumentException("Invalid city: " + cityName);
    }

}
