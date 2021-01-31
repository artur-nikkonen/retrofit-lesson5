package ru.geekbrains.lesson5.product.create;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.dto.StringsProduct;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class CreateProductPriceTests extends BaseProductTests {

    @SneakyThrows
    @Test
    void createProductWithoutPriceTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
    }

    @SneakyThrows
    @Test
    void createProductWithNegativePriceTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withPrice(Integer.toString(-(int) (Math.random() * 10000)))
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
    }

    @SneakyThrows
    @Test
    void createProductWithDoublePriceTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withPrice("1.1")
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
    }

    @SneakyThrows
    @Test
    void createProductWithMaxPriceTest() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((long)Integer.MAX_VALUE)
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(product);

        assertThat(response.isSuccessful(), is(true));
    }

    @SneakyThrows
    @Test
    void createProductWithTooBigPriceTest() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((long)Integer.MAX_VALUE + 1)
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(product);
        assertThat(response.isSuccessful(), is(false));
    }

    @SneakyThrows
    @Test
    void createProductWithSymbolsTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withPrice("!\";%:?*(){}[]\\'.,`~^$")
                .withCategoryTitle(FOOD.title);

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
    }
}
