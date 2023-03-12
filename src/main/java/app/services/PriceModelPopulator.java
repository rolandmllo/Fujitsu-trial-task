package app.services;

import app.Dao.WeatherRepository;
import app.model.PriceModel;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceModelPopulator {

    @Autowired
    private SearchService searchService;
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    private DeliveryFeeCalculator deliveryFeeCalculator;

    private PriceModel priceModel;

    public PriceModel Populate(String cityInput, String vehicleInput){
        priceModel = new PriceModel();

        searchService.ReadInputs(cityInput, vehicleInput, priceModel);
        addLatestWeatherToPriceModel(priceModel);

        deliveryFeeCalculator.calculateFees(priceModel);

        return priceModel;
    }

    private void addLatestWeatherToPriceModel(PriceModel priceModel) {
        Weather weather = weatherRepository
                .findFirstByObservationStationNameIsOrderByTimestampDesc
                        (priceModel.getCity().getWeatherObservationStation());
        priceModel.setWeather(weather);
    }
}
