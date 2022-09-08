import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    static Faker faker = new Faker();
    private final IngredientType ingredientType;
    private Ingredient ingredient;
    private String ingredientName;
    private float ingredientPrice;

    public IngredientParameterizedTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }


    @Parameterized.Parameters(name = "Тип: {0}.  Название: {1}. Цена: {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {SAUCE, faker.food().ingredient(), new Random().nextFloat()},
                {FILLING, faker.food().ingredient(), new Random().nextFloat()}
        };
    }

    @Before
    public void setup() {
        ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
    }

    @Test
    public void getTypeTest() {
        String result = String.valueOf(ingredient.getType());
        Assert.assertEquals(String.valueOf(ingredientType), result);
    }

    @Test
    public void getNameTest() {
        String result = ingredient.getName();
        Assert.assertEquals(ingredientName, result);
    }

    @Test
    public void getPriceTest() {
        float result = ingredient.getPrice();
        Assert.assertEquals(ingredientPrice, result, 0.01f);
    }
}