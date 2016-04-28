package factories;

import builders.FoodBuilder;

//Sonar requests FunctionalInterface annotation here.
public interface FoodBuilderFactory {//NOSONAR
    FoodBuilder getBuilder(String type);
}
