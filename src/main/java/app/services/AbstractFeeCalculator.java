package app.services;

import app.model.PriceModel;

public abstract class AbstractFeeCalculator {

    protected abstract PriceModel applyFeeRate(PriceModel priceModel);

}
