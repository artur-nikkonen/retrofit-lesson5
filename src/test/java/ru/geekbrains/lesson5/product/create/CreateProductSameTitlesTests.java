package ru.geekbrains.lesson5.product.create;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class CreateProductSameTitlesTests extends BaseProductTests {

    @SneakyThrows
    @Test
    void createProductSameTitlesTest() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        createProduct(product);

        Response<Product> response = productService.createProduct(product).execute();

        if(response.isSuccessful())
        {
            int secondProductId = response.body().getId();
            productService.deleteProduct(secondProductId);
        }

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(404));
    }


}
