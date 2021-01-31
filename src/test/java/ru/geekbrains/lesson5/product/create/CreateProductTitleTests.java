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

public class CreateProductTitleTests extends BaseProductTests {

    static Stream<String> titles() {
        return Stream.of(faker.food().fruit() + " " + faker.food().fruit(), // несколько слов
                faker.food().fruit() + "\n" + faker.food().fruit(), // могострочный заголовок
                "     " + faker.food().fruit() + "     ", // заголовок с внешними пробелами
                StringUtils.repeat("q", 255),  // длинный закголовок
                "!\";%:?*(){}[]\\'.,`~^$", // закголовок со спецсимволами
                "абвгд АБВГД" //русские буквы
        );
    }

    static Stream<String> blankStrings() {
        return Stream.of("", "   ", null, "\n", "\r", "\r\n", "\t");
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("titles")
    void createProductWithMultilineTitleTest(String title) {
        Product product = new Product()
                .withTitle(title)
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);

        CheckProductResponseIs201(response);
        CompareProducts(product, response.body(), false);
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("blankStrings")
    void createProductProblemTitleTest() {

        Product product = new Product()
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(404)); //надо уточнить код ошибки!!!
    }

    @SneakyThrows
    @Test
    void createProductWithTooLongTitle() {
        Product product = new Product()
                .withTitle(StringUtils.repeat("q", 256))
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = createProduct(product);

        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(400));
    }
}
