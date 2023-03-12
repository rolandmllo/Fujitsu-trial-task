package app.services;

import app.model.PriceModel;

/**
 * The type Abstract fee calculator.
 */
public abstract class AbstractFeeCalculator {

    /**
     * Apply fee rate price model to all FeeCalculatorClasses.
     *
     * @param priceModel the price model
     * @return the price model
     */
    protected abstract PriceModel applyFeeRate(PriceModel priceModel);

}
