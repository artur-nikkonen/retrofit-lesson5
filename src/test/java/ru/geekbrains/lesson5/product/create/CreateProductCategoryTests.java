package ru.geekbrains.lesson5.product.create;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.dto.StringsProduct;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class CreateProductCategoryTests extends BaseProductTests {
    @SneakyThrows
    @Test
    void createProductWithoutCategoryTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withPrice("100");

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(404));
    }

    @SneakyThrows
    @Test
    void createProductWithBadCategoryTest() {
        StringsProduct stringsProduct = new StringsProduct()
                .withTitle(faker.food().fruit())
                .withPrice("100")
                .withCategoryTitle("abc");

        Response<Product> response = createProduct(stringsProduct);

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(404));
    }
}
