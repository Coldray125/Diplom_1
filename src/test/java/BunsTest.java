import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import java.util.Random;

public class BunsTest {

    private Bun bun;
    private static Faker faker = new Faker();
    private final String bunName = faker.food().ingredient();
    float bunPrice = new Random().nextFloat();

    @Before
    public void setup() {
        bun = new Bun(bunName, bunPrice);
    }

    @Test
    public void getBunName() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bun.getName(), bunName);
    }

    @Test
    public void getBunPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bun.getPrice(), bunPrice, 0.01f);
    }
}
