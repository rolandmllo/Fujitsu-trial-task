package app.model;

import lombok.Data;

@Data
public class PriceModel {
    private City city;
    private Vehicle vehicle;
    private Weather weather;
    private RegionalBaseFee regionalBaseFee;
    private WindSpeedExtraFee windSpeedExtraFee;
    private WeatherPhenomenonExtraFee weatherPhenomenonExtraFee;
    private Boolean forbidden = false;
    private Double totalPrice;

}
