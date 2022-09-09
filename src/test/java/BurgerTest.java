import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    static Faker faker = new Faker();
    private final String bunName = faker.food().ingredient();
    private final float bunPrice = new Random().nextFloat();
    private final String ingredientOneName = faker.food().ingredient();
    private final String ingredientTwoName = faker.food().ingredient();
    private final float ingredientOnePrice = new Random().nextFloat();
    private final float ingredientTwoPrice = new Random().nextFloat();

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Before
    public void setup() {
        burger = new Burger();
        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn(bunName);
        when(ingredient1.getPrice()).thenReturn(ingredientOnePrice);
        when(ingredient1.getName()).thenReturn(ingredientOneName);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient2.getPrice()).thenReturn(ingredientTwoPrice);
        when(ingredient2.getName()).thenReturn(ingredientTwoName);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBurgerBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(burger.bun, bun);
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(List.of(ingredient1, ingredient2), burger.ingredients);
    }

    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getBurgerPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        float result = bunPrice * 2 + ingredientOnePrice;
        Assert.assertEquals(burger.getPrice(), result, 0.01f);
    }

    @Test
    public void getBurgerReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        String actual = burger.getReceipt();
        String expected = String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                bunName, ingredient1.getType().toString().toLowerCase(), ingredientOneName, bunName, burger.getPrice());
        Assert.assertEquals(actual, expected);
    }
}