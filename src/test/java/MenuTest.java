import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * An important restaurant is trying to get their services upgraded
 * attempting to offer an online menu where customers can check easily which
 * dishes are being offered. The system must allow restaurant's manager to
 * easily add new dishes along with its ingredients. We're aiming for a
 * customer experience where customers can find all dishes with a given
 * ingredient (in case he/she/them want to eat something specific) or
 * which doesn't contain a given ingredient (ex: vegetarians who wants
 * meatless dishes).
 *
 * Dishes' names are represented by a String. Dishes' ingredients are also
 * represented by Strings and a dish may have one or more ingredients.
 *
 * A consultancy service was hired to write all following test cases,
 * with the business rules that menu system must cover.
 *
 * Implement a Menu system that covers for all the following test cases.
 * Test cases code must not be changed.
 */
public class MenuTest {
    
    private Menu menu;

    @Test
    public void getRegisteredDish() {
        this.menu.addDish("Hot dog", "Bread", "Sausage");
        
        assertTrue(this.menu.getDishes().contains("Hot dog"));
    }
    
    @Test
    public void getAllRegisteredDishes() {
        this.menu.addDish("Lemonade", "Water", "Lemon", "Sugar");
        this.menu.addDish("Orange juice", "Orange");
        this.menu.addDish("Sparkling water", "Water");

        final List<String> dishes = this.menu.getDishes();

        assertTrue(dishes.contains("Lemonade"));
        assertTrue(dishes.contains("Orange juice"));
        assertTrue(dishes.contains("Sparkling water"));
    }
    
    @Test
    public void getDishIngredients() throws UnregisteredDishException {
        this.menu.addDish("Guacamole", "Avocado", "Tomato", "Onion", "Lemon");

        final List<String> ingredients = this.menu.getIngredientsFor("Guacamole");

        assertTrue(ingredients.contains("Avocado"));
        assertTrue(ingredients.contains("Tomato"));
        assertTrue(ingredients.contains("Onion"));
        assertTrue(ingredients.contains("Lemon"));
    }
    
    @Test(expected = UnregisteredDishException.class)
    public void throwExceptionOnUnregisteredDish() throws UnregisteredDishException {
        this.menu.addDish("Chili", "Meat", "Beans", "Tomato sauce");
        
        this.menu.getIngredientsFor("Quesadilla");
    }
    
    @Test
    public void getDishesWithIngredient() {
        this.menu.addDish("Hamburger", "Bread", "Meat");
        this.menu.addDish("Cheeseburger", "Bread", "Meat", "Cheese");
        this.menu.addDish("Grilled ham and cheese", "Bread", "Ham", "Cheese");
        this.menu.addDish("Toasted bread", "Bread", "Butter");

        final List<String> cheeseDishes = this.menu.getDishesWith("Cheese");

        assertTrue(cheeseDishes.contains("Cheeseburger"));
        assertTrue(cheeseDishes.contains("Grilled ham and cheese"));
    }
    
    @Test
    public void getDishesWithoutIngredient() {
        this.menu.addDish("Hamburger", "Bread", "Meat");
        this.menu.addDish("Cheeseburger", "Bread", "Meat", "Cheese");
        this.menu.addDish("Grilled ham and cheese", "Bread", "Ham", "Cheese");
        this.menu.addDish("Toasted bread", "Bread", "Butter");

        final List<String> meatlessDishes = this.menu.getDishesWithout("Meat");

        assertTrue(meatlessDishes.contains("Grilled ham and cheese"));
        assertTrue(meatlessDishes.contains("Toasted bread"));
    }
    
    @Before
    public void setup() {
        this.menu = new Menu();
    }
    
}
