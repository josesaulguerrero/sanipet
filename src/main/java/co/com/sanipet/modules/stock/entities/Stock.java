package co.com.sanipet.modules.stock.entities;

public class Stock {
    private Integer amount;

    public Stock(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

