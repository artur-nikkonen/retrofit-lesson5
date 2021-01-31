package ru.geekbraind.lesson5.sevices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.geekbraind.lesson5.dto.Product;
import ru.geekbraind.lesson5.dto.StringsProduct;

public interface ProductService {

    @GET("products")
    Call<Product[]> getProducts();

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @POST("products")
    Call<Product> createProduct(@Body StringsProduct product);

    @PUT("products")
    Call<Product> updateProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") String id);

}
