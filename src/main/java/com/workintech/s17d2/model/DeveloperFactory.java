package com.workintech.s17d2.model;

import com.workintech.s17d2.tax.Taxable;

public class DeveloperFactory {

    public static Developer create(Developer developer, Taxable taxable){
        Developer newDeveloper = null;
        if(developer.getExperience().name().equalsIgnoreCase("junior")){
            newDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getJuniorTaxRate()));
        } else if (developer.getExperience().name().equalsIgnoreCase("mid")){
            newDeveloper = new MidDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getMiddleTaxRate()));
        } else if (developer.getExperience().name().equalsIgnoreCase("senior")) {
            newDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getSeniorTaxRate()));
        }
        return newDeveloper;
    }
}
