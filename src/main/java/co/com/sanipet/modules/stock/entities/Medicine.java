package co.com.sanipet.modules.stock.entities;

/*
* Class that represents the multiple types of medicine.
*/
public class Medicine {
    // Attributes
    protected String name;
    protected MedicinePresentation presentation;
    protected MedicineUsage usage;
    protected Quantity quantity;
    protected Stock stock;

    //Constructor
    public Medicine(String name, MedicinePresentation presentation, MedicineUsage usage, Quantity quantity, Stock stock) {
        this.name = name;
        this.presentation = presentation;
        this.usage = usage;
        this.quantity = quantity;
        this.stock = stock;
    }

    /*
    *  Gets the name of the medicine
    */
    public String getName() {
        return name;
    }

    /*
    *  Gets the presentation of the medicine
    */
    public MedicinePresentation getPresentation() {
        return presentation;
    }

    /*
    *  Gets the type of medicine usage of the medicine
    */
    public MedicineUsage getUsage() {
        return usage;
    }

    /*
    *  Gets the quantity of the medicine
    */
    public Quantity getQuantity() {
        return quantity;
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
    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", presentation=" + presentation +
                ", usage=" + usage +
                ", quantity=" + quantity +
                ", stock=" + stock +
                '}';
    }
}

