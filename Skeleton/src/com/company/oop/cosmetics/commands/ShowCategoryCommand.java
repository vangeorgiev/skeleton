package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.validator.ConstraintValidator;

import java.util.List;

public class ShowCategoryCommand implements Command {

    private final ProductRepository productRepository;
    private final ConstraintValidator validator = new ConstraintValidator();

    public ShowCategoryCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        validator.validateParameter(parameters, 1, "ShowCategory");

        String categoryName = parameters.get(0);

        return showCategory(categoryName);
    }

    private String showCategory(String categoryName) {
        Category category = productRepository.findCategoryByName(categoryName);

        return category.print();
    }

}
