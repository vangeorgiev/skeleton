package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.validator.ConstraintValidator;

public class ProductImpl implements Product {

    private String name;
    private String brand;
    private double price;
    private final GenderType gender;
    private final ConstraintValidator validator = new ConstraintValidator();

    public ProductImpl(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        validator.validatePrice(price);
        this.price = price;
    }

    public GenderType getGender() {
        return gender;
    }

    @Override
    public String print() {
        return String.format(
                "#%s %s%n" +
                " #Price: $%.2f%n" +
                " #Gender: %s%n",
                name,
                brand,
                price,
                gender);
    }
}
