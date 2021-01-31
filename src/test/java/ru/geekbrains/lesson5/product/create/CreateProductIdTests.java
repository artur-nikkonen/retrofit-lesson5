package ru.geekbrains.lesson5.product.create;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class CreateProductIdTests extends BaseProductTests {

    @SneakyThrows
    @Test
    void createProductWithIdTest() {

        Product product = new Product()
                .withId(100)
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(400));
    }
}
