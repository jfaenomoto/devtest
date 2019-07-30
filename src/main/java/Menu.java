import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Menu {

    private Map<String, List<String>> dishes;

    public Menu() {
        this.dishes = new HashMap<>();
    }

    void addDish(String name, String... ingredients) {
        this.dishes.put(name, Arrays.asList(ingredients));
    }

    List<String> getDishes() {
        return new ArrayList<>(this.dishes.keySet());
    }

    List<String> getIngredientsFor(String dish) throws UnregisteredDishException {
        if (!this.dishes.containsKey(dish)) {
            throw new UnregisteredDishException();
        }
        return this.dishes.get(dish);
    }

    List<String> getDishesWith(String ingredient) {
        return this.dishes.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> entry.getValue().contains(ingredient))
                .map(entry -> entry.getKey())
                .collect(toList());
    }

    List<String> getDishesWithout(String ingredient) {
        return this.dishes.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !entry.getValue().contains(ingredient))
                .map(entry -> entry.getKey())
                .collect(toList());
    }

}

class UnregisteredDishException extends Exception {

}
