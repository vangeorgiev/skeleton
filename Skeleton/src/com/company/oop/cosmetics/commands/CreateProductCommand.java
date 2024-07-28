package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.validator.ConstraintValidator;

import java.util.List;

public class CreateProductCommand implements Command {

    private static final String PRODUCT_CREATED = "Product with name %s was created!";

    private final ProductRepository productRepository;

    private final ConstraintValidator validator = new ConstraintValidator();

    public CreateProductCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validator.validateParameter(parameters, 4, "CreateProduct");

        String name = parameters.get(0);
        validator.validateName(name);

        String brand = parameters.get(1);
        validator.validateBrand(brand);

        double price = validator.validatePriceFormat(parameters.get(2));
        GenderType gender = validator.validateGenderType(parameters.get(3));

        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        validator.validateUniqueName(name, productRepository.productExist(name), "Product");

        productRepository.createProduct(name, brand, price, gender);

        return String.format(PRODUCT_CREATED, name);
    }

}
