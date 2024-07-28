package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.commands.*;
import com.company.oop.cosmetics.core.contracts.CommandFactory;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.exceptions.category.InvalidArgumentException;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeValue, ProductRepository productRepository) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandTypeValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Command " + commandTypeValue + " is not supported.");
        }

        switch (commandType) {
            case CREATECATEGORY:
                return new CreateCategoryCommand(productRepository);
            case CREATEPRODUCT:
                return new CreateProductCommand(productRepository);
            case ADDPRODUCTTOCATEGORY:
                return new AddProductToCategoryCommand(productRepository);
            case SHOWCATEGORY:
                return new ShowCategoryCommand(productRepository);
            default:
                //TODO Can we improve this code?
                return null;
        }


    }

}
