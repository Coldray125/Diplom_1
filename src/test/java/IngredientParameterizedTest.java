import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    Faker faker = new Faker();
    private final IngredientType ingredientType;
    private Ingredient ingredient;
    private static String ingredientName;
    private static float ingredientPrice;

    public IngredientParameterizedTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        IngredientParameterizedTest.ingredientName = ingredientName;
        IngredientParameterizedTest.ingredientPrice = ingredientPrice;
    }


    @Parameterized.Parameters()
    public static Object[][] getTestData() {
        return new Object[][]{
                {IngredientType.SAUCE, ingredientName, ingredientPrice},
                {IngredientType.FILLING, ingredientName, ingredientPrice}
        };
    }

    @Before
    public void setup() {
        ingredientName = faker.food().ingredient();
        ingredientPrice = new Random().nextFloat();
        ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
    }

    @Test
    public void getTypeTest() {
        IngredientType result = ingredient.getType();
        Assert.assertEquals(ingredientType, result);
    }

    @Test
    public void getNameTest() {
        String result = ingredient.getName();
        Assert.assertEquals(ingredientName, result);
    }

    @Test
    public void getPriceTest() {
        float result = ingredient.getPrice();
        Assert.assertEquals(ingredientPrice, result,0.01f);
    }
}