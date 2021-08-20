package it.gabrieletondi.telldontask.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price; // units?
    private Category category;

    public BigDecimal calculateTax() {
        return price
            .divide(valueOf(100))
            .multiply(taxPercentage())
            .setScale(2, HALF_UP);
    }

    private BigDecimal taxPercentage() {
        return getCategory().getTaxPercentage();
    }

    public BigDecimal priceWithTax() {
        return getPrice().add(calculateTax()).setScale(2, HALF_UP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
