package com.sda.weather.location;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationMapperTest {

    private LocationMapper locationMapper = new LocationMapper();

    @Test
    void asLocationDTO_returnsLocationDTO() {
        // given
        Location location = new Location("Gdansk", "Pomorskie", "Polska", 50f, 40f);
        location.setId(10L);

        // when
        LocationDTO locationDTO = locationMapper.asLocationDTO(location);

        // then
        assertThat(locationDTO.getId()).isEqualTo(10L);
        assertThat(locationDTO.getCity()).isEqualTo("Gdansk");
    }
}
