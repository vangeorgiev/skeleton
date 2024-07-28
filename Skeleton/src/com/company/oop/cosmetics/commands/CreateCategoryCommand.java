package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.validator.ConstraintValidator;

import java.util.List;

public class CreateCategoryCommand implements Command {

    private static final String CATEGORY_CREATED = "Category with name %s was created!";

    private final ProductRepository productRepository;

    private final ConstraintValidator validator = new ConstraintValidator();

    public CreateCategoryCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validator.validateParameter(parameters, 1, "CreateCategory");

        String categoryName = parameters.get(0);

        return createCategory(categoryName);
    }

    private String createCategory(String categoryName) {
        validator.validateUniqueName(categoryName, productRepository.categoryExist(categoryName), "Category");

        productRepository.createCategory(categoryName);

        return String.format(CATEGORY_CREATED, categoryName);
    }

}
