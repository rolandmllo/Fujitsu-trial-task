package app.services;

import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    VehicleRepository vehicleRepository;


    public PriceModel ReadInputs(String cityInput, String vehicleInput, PriceModel priceModel){

        // TODO: search validation
        City city = GetCity(cityInput.replace("city=", "").trim());
        Vehicle vehicle = GetVehicle(vehicleInput.replace("vehicle=", "").trim());

        priceModel.setCity(city);
        priceModel.setVehicle(vehicle);

        return priceModel;
    }

    private Vehicle GetVehicle(String vehicle) {
        return vehicleRepository.findByVehicleName(vehicle);
    }

    private City GetCity(String city){
        return cityRepository.findByCityName(city);
    }

}
