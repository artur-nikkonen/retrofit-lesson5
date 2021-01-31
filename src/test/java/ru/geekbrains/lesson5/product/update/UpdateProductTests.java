package ru.geekbrains.lesson5.product.update;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.ELECTRONIC;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class UpdateProductTests extends BaseProductTests {

    @SneakyThrows
    @BeforeEach
    void setUp() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);
        setProductId(response.body().getId());
    }

    @Test
    void UpdateProductPositiveTest() throws IOException {

        Product product = new Product()
                .withId(getProductId())
                .withTitle(faker.food().fruit())
                .withCategoryTitle(ELECTRONIC.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> updateResponse = productService.updateProduct(product).execute();
        assertThat(updateResponse.isSuccessful(), is(true));

        CompareProducts(product, updateResponse.body(), true);
    }
}
