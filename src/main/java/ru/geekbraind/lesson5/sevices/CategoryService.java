package ru.geekbraind.lesson5.sevices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.geekbraind.lesson5.dto.GetCategoryResponce;

public interface CategoryService {

    @GET("categories/{id}")
    Call<GetCategoryResponce> getCategory(@Path("id") int id);

    @GET("categories/{id}")
    Call<GetCategoryResponce> getCategory(@Path("id") String id);
}
