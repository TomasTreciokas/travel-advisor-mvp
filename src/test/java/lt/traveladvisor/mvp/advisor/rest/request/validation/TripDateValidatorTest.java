package lt.traveladvisor.mvp.advisor.rest.request.validation;

import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class TripDateValidatorTest {

    private TripDateValidator tripDateValidator;

    @BeforeEach
    public void setUp() {
        tripDateValidator = new TripDateValidator();
    }

    @ParameterizedTest
    @MethodSource("provideTestingData")
    void validate(AdviseRequest mockRequest, boolean expectedResult) {
        assertThat(tripDateValidator.isValid(mockRequest, null)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideTestingData() {
        return Stream.of(
                Arguments.of(createAdviseRequest(LocalDate.of(2020, 10, 27), LocalDate.of(2020, 12,10)), false),
                Arguments.of(createAdviseRequest(LocalDate.of(2022, 10, 27), LocalDate.of(2022, 12,10)), true),
                Arguments.of(createAdviseRequest(LocalDate.of(2017, 10, 27), LocalDate.of(2019, 12,10)), false)
        );
    }

    private static AdviseRequest createAdviseRequest(LocalDate startDate, LocalDate endDate) {
        return new AdviseRequest(startDate, endDate, 0, null, null);
    }
}