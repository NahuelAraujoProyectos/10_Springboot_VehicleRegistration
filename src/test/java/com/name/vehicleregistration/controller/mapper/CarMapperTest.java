package com.name.vehicleregistration.controller.mapper;

import com.name.vehicleregistration.controller.dtos.BrandResponse;
import com.name.vehicleregistration.controller.dtos.CarRequest;
import com.name.vehicleregistration.controller.dtos.CarResponse;
import com.name.vehicleregistration.controller.mappers.BrandMapper;
import com.name.vehicleregistration.controller.mappers.CarMapper;
import com.name.vehicleregistration.entity.BrandEntity;
import com.name.vehicleregistration.model.Brand;
import com.name.vehicleregistration.model.Car;
import com.name.vehicleregistration.repository.BrandRepository;
import com.name.vehicleregistration.service.converters.BrandConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {

    @InjectMocks
    private CarMapper carMapper;

    @Mock
    private BrandConverter brandConverter;

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private BrandRepository brandRepository;

    @Test
    void testToResponse() {
        // Given
        Brand brand = Brand.builder()
                .id(1)
                .name("Toyota")
                .country("Japan")
                .build();

        Car car = Car.builder()
                .id(1)
                .model("Corolla")
                .milleage(100000)
                .price(15000.0)
                .modelYear(2015)
                .description("Sedan de 4 puertas")
                .colour("Rojo")
                .fuelType("Gasolina")
                .numDoors(4)
                .brand(brand)
                .build();

        BrandResponse brandResponse = BrandResponse.builder()
                .id(1)
                .name("Toyota")
                .country("Japan")
                .build();

        when(brandMapper.toResponse(brand)).thenReturn(brandResponse);

        // When
        CarResponse carResponse = carMapper.toResponse(car);

        // Then
        assertEquals(1, carResponse.getId());
        assertEquals("Toyota", carResponse.getBrand().getName());
        assertEquals("Corolla", carResponse.getModel());
        assertEquals(100000, carResponse.getMilleage());
        assertEquals(15000.0, carResponse.getPrice());
        assertEquals(2015, carResponse.getModelYear());
        assertEquals("Sedan de 4 puertas", carResponse.getDescription());
        assertEquals("Rojo", carResponse.getColour());
        assertEquals("Gasolina", carResponse.getFuelType());
        assertEquals(4, carResponse.getNumDoors());
    }
}
