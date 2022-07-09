package co.com.sanipet.modules.stock.entities;

import co.com.sanipet.utils.HashGenerator;

/*
* Class that represents the multiple types of medicine.
*/
public class Medicine {
    // Attributes
    private final String name;
    private final String id;
    private final MedicinePresentation presentation;
    private Stock stock;

    //Constructor
    public Medicine(String name, MedicinePresentation presentation, Stock stock) {
        this.name = name;
        this.id = HashGenerator.generateRandomAlphanumericString(20);
        this.presentation = presentation;
        this.stock = stock;
    }

    /*
    *  Gets the name of the medicine
    */
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /*
    *  Gets the presentation of the medicine
    */
    public MedicinePresentation getPresentation() {
        return presentation;
    }

    /*
    *  Gets the stock of the medicine
    */
    public Stock getStock() {
        return stock;
    }

    /*
    * @Override the method toString()
    */

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", presentation=" + presentation +
                ", stock=" + stock +
                '}';
    }
}

