package ru.geekbrains.lesson5.product.base;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.dto.StringsProduct;
import ru.geekbraind.lesson5.sevices.ProductService;
import ru.geekbraind.lesson5.utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public abstract class BaseProductTests {
    static protected ProductService productService = RetrofitUtils.getProductService();
    static protected Faker faker = new Faker();
    Integer productId;

    protected void setProductId(Integer productId) {
        this.productId = productId;
    }

    protected Integer getProductId() {
        return productId;
    }


    @BeforeEach
    void setUp() {
        productId = null;
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        if (productId != null) {
            Response<ResponseBody> response = productService.deleteProduct(productId).execute();
            assertThat(response.isSuccessful(), is(true));
        }
    }

    protected Response<Product> createProduct(Product product) throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        if (response.isSuccessful()) {
            productId = response.body().getId();
        }
        return response;
    }

    protected Response<Product> createProduct(StringsProduct stringsProduct) throws IOException {
        Response<Product> response = productService.createProduct(stringsProduct).execute();
        if (response.isSuccessful()) {
            productId = response.body().getId();
        }
        return response;
    }

    protected void CompareProducts(Product product, Product responseProduct, Boolean withId) {

        if (withId) {
            assertThat(product.getId(), is(responseProduct.getId()));
        }

        assertThat(product.getTitle(), equalTo(responseProduct.getTitle()));
        assertThat(product.getPrice(), is(responseProduct.getPrice()));
        assertThat(product.getCategoryTitle(), equalTo(responseProduct.getCategoryTitle()));
    }

    protected void CheckProductResponseIs201(Response<Product> response) {
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.code(), is(201));
    }

}
