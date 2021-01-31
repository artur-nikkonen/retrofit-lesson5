package ru.geekbrains.lesson5.category;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;
import ru.geekbraind.lesson5.dto.GetCategoryResponce;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbrains.lesson5.category.GetCategoryPositiveTests.categoryService;

public class GetCategoryNegativeTests {

    private static Stream<Arguments> ids404() {
        return Stream.of(
                Arguments.of(Integer.toString(100 + (int) (Math.random() * 10000))), //несуществующий id
                Arguments.of(Integer.toString(-(int) (Math.random() * 10000))), //отрицательный id
                Arguments.of(Long.toString(Long.MAX_VALUE)), // max int64
                Arguments.of("") // пустой id
        );
    }

    private static Stream<Arguments> ids400() {
        return Stream.of(
                Arguments.of("abcde"), //строка
                Arguments.of(Double.toString(Math.random())), //double
                Arguments.of(Double.toString(Math.random()).replace(".", ",")), //double с запятой
                Arguments.of("9223372036854775808") // max int64 + 1
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("ids404")
    void unegative404Test(String id) {
        Response<GetCategoryResponce> response = categoryService.getCategory(id).execute();
        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(404));
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("ids400")
    void unegative400Test(String id) {
        Response<GetCategoryResponce> response = categoryService.getCategory(id).execute();
        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(400));
    }

}
