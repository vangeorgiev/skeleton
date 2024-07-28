package com.company.oop.cosmetics.validator;

import com.company.oop.cosmetics.exceptions.category.InvalidArgumentException;
import com.company.oop.cosmetics.exceptions.category.InvalidCommandException;
import com.company.oop.cosmetics.models.GenderType;

import java.util.List;

public class ConstraintValidator {
    private static final int NAME_MIN_SIZE = 3;
    private static final int MAX_SIZE = 10;
    private static final int BRAND_MIN_SIZE = 2;

    public void validateName(String name) {
        if (name.length() < NAME_MIN_SIZE || name.length() > MAX_SIZE) {
            throw new InvalidArgumentException("Category name should be between " + NAME_MIN_SIZE + " and " + MAX_SIZE + " symbols.");
        }
    }

    public void validateBrand(String brand) {
        if (brand.length() < BRAND_MIN_SIZE || brand.length() > MAX_SIZE) {
            throw new InvalidArgumentException("Product brand should be between " + BRAND_MIN_SIZE + " and " + MAX_SIZE + " symbols.");
        }
    }

    public void validatePrice(Double price) {
        if (price < 0) {
            throw new InvalidArgumentException("Price can't be negative.");
        }
    }

    public void validateParameter(List<String> parameters, int expectedNumberOfParameters, String invokedByClass) {
        if (!(parameters.size() == expectedNumberOfParameters)) {
            throw new ArrayIndexOutOfBoundsException(invokedByClass + " command expects " + expectedNumberOfParameters + " parameters.");
        }
    }

    public void validateUniqueName(String nameToValidate, boolean isExists, String category) {
        if (isExists) {
            throw new InvalidArgumentException(category + " " + nameToValidate + " already exists.");
        }
    }

    public Double validatePriceFormat(String priceToValidate) {
        double price;
        try {
            price = Double.parseDouble(priceToValidate);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Third parameter should be price (real number)");
        }

        return price;
    }

    public GenderType validateGenderType(String genderToValidate) {
        GenderType gender;
        try {
            gender = GenderType.valueOf(genderToValidate.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Forth parameter should be one of Men, Women or Unisex.");
        }

        return gender;
    }
}
