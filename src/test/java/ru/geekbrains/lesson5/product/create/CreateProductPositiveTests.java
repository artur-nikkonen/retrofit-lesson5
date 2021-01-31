package ru.geekbrains.lesson5.product.create;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.sevices.ProductService;
import ru.geekbraind.lesson5.utils.RetrofitUtils;
import ru.geekbrains.lesson5.product.base.BaseProductTests;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class CreateProductPositiveTests extends BaseProductTests {

    @SneakyThrows
    @Test
    void createProductWithFoodCategoryTest() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);

        CheckProductResponseIs201(response);
        CompareProducts(product, response.body(), false);
    }
}
