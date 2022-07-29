package com.sda.weather.forecast;

import com.sda.weather.exception.InternalServerException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class WindDirectionMapperTest {

    private final WindDirectionMapper windDirectionMapper = new WindDirectionMapper();

    @ParameterizedTest
    @CsvSource({
            "0, N", "5, N", "10, N", "11, N", "12, NNE", "15, NNE", "20, NNE", "30, NNE", "90, E", "180, S", "270, W", "360, N"
    })
    void mapWindDirection_returnsCorrectValue(int degree, String expectedValue) {
        // when
        String result = windDirectionMapper.mapWindDirection(degree);

        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -100, -1, 361, 720, Integer.MAX_VALUE})
    void mapWindDirection_whenValueIsOutOfRange_throwsException(int degree) {
        // when
        Throwable result = catchThrowable(() -> windDirectionMapper.mapWindDirection(degree));

        // then
        assertThat(result).isExactlyInstanceOf(InternalServerException.class);
    }
}
