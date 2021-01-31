package ru.geekbrains.lesson5.product.delete;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.sevices.ProductService;
import ru.geekbraind.lesson5.utils.RetrofitUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class DeleteProductTests {

    static protected ProductService productService = RetrofitUtils.getProductService();
    static protected Faker faker = new Faker();

    @SneakyThrows
    @Test
    void DeleteExistsProduct() {
        Product product = new Product()
                .withTitle(faker.food().fruit())
                .withCategoryTitle(FOOD.title)
                .withPrice((long) (Math.random() * 1000 + 1));

        Response<Product> response = productService.createProduct(product).execute();
        Response<ResponseBody> deleteResponce = productService.deleteProduct(response.body().getId()).execute();

        assertThat(deleteResponce.isSuccessful(), is(true));
    }


    @SneakyThrows
    @Test
    void DeleteUnexistsProduct() {
        Response<ResponseBody> deleteResponce = productService.deleteProduct(100000).execute();

        assertThat(deleteResponce.isSuccessful(), is(false));
        assertThat(deleteResponce.code(), is(403));
    }

    @SneakyThrows
    @Test
    void DeleteWithoutIDProduct() {
        Response<ResponseBody> deleteResponce = productService.deleteProduct("").execute();

        assertThat(deleteResponce.isSuccessful(), is(false));
        assertThat(deleteResponce.code(), is(405));
    }

}
