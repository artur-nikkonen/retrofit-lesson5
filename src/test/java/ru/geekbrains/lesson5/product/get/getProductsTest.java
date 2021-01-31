package ru.geekbrains.lesson5.product.get;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.sevices.ProductService;
import ru.geekbraind.lesson5.utils.RetrofitUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class getProductsTest {
    static ProductService productService = RetrofitUtils.getProductService();

    //1. Надо чистить базу и заполнять ее перед тестами, чтобы точно знать, какие в ней есть продукты
    //2. Надо проверить возвращение пустого списка после удаления всех записей

    @SneakyThrows
    @Test
    void getProductsTest() {
        Response<Product[]> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.code(), is(200));
    }
}
