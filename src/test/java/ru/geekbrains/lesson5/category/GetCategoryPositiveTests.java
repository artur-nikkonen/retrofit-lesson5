package ru.geekbrains.lesson5.category;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.GetCategoryResponce;
import ru.geekbraind.lesson5.sevices.CategoryService;
import ru.geekbraind.lesson5.utils.RetrofitUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.geekbraind.lesson5.base.enums.Categories.FOOD;

public class GetCategoryPositiveTests {

    static CategoryService categoryService = RetrofitUtils.getCategoryService();

    @SneakyThrows
    @Test
    void byIdPositiveTest() {
        Response<GetCategoryResponce> response = categoryService.getCategory(1).execute();
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.code(), is(200));
        assertThat(response.body().getId(), equalTo(FOOD.id));
        assertThat(response.body().getTitle(), equalTo(FOOD.title));

        response.body().getProducts().forEach(
                p -> assertThat(p.getCategoryTitle(), equalTo(response.body().getTitle()))
        );
    }
}
