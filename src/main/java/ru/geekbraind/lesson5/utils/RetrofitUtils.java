package ru.geekbraind.lesson5.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.geekbraind.lesson5.sevices.CategoryService;
import ru.geekbraind.lesson5.sevices.ProductService;

import static okhttp3.logging.HttpLoggingInterceptor.Level.*;

public class RetrofitUtils {

    private static HttpLoggingInterceptor logging;
    private static OkHttpClient.Builder httpClient;

    static {
        logging = new HttpLoggingInterceptor(new ArturLogger());
        httpClient = new OkHttpClient.Builder();
        logging.setLevel(BODY);
        httpClient.addInterceptor(logging);
    }

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static CategoryService getCategoryService() {
        return getRetrofit().create(CategoryService.class);
    }

    public static ProductService getProductService() {
        return getRetrofit().create(ProductService.class);
    }


}
