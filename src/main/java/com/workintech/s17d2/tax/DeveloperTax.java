package com.workintech.s17d2.tax;

import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable {
    @Override
    public double getJuniorTaxRate() {
        return 0.2;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.3;
    }

    @Override
    public double getSeniorTaxRate() {
        return 0.4;
    }
}
