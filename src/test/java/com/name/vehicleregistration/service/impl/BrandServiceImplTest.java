package com.name.vehicleregistration.service.impl;

import com.name.vehicleregistration.entity.BrandEntity;
import com.name.vehicleregistration.exception.brand.BrandAlreadyExistsException;
import com.name.vehicleregistration.exception.brand.BrandNotFoundException;
import com.name.vehicleregistration.model.Brand;
import com.name.vehicleregistration.repository.BrandRepository;
import com.name.vehicleregistration.service.converters.BrandConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
    @InjectMocks
    private BrandServiceImpl brandService;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    public BrandConverter brandConverter;

    @Test
    void addBrand_test() {
        // Given
        String brandName = "BrandName";
        Brand brand = new Brand();
        brand.setName(brandName);

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(brandName);

        // When
        when(brandRepository.existsByName(brandName)).thenReturn(false);
        when(brandConverter.toEntity(brand)).thenReturn(brandEntity);
        when(brandRepository.save(brandEntity)).thenReturn(brandEntity);
        when(brandConverter.toModel(brandEntity)).thenReturn(brand);

        // Then
        Brand result = brandService.addBrand(brand);
        assertNotNull(result);
        assertEquals(brandName, result.getName());
    }

    @Test
    void addBrand_test_ko() {
        // Given
        Brand brand = Brand.builder()
                .name("BrandName")
                .build();

        BrandEntity brandEntity = BrandEntity.builder()
                .name("BrandName")
                .build();

        // When
        when(brandConverter.toEntity(brand)).thenReturn(brandEntity);
        when(brandRepository.existsByName(brandEntity.getName())).thenReturn(true);

        // Then
        BrandAlreadyExistsException exception = assertThrows(BrandAlreadyExistsException.class, () -> {
            brandService.addBrand(brand);
        });
        assertEquals("Brand with name 'BrandName' already exists.", exception.getMessage());
    }


    @Test
    void getBrandById_test() {
        // Given
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        //-------------------------------------------
        Brand brand = new Brand();
        brand.setId(1);
        // When
        when(brandRepository.findById(1)).thenReturn(Optional.of(brandEntity));
        when(brandConverter.toModel(brandEntity)).thenReturn(brand);
        // Then
        Brand result = brandService.getBrandById(1);
        assertEquals(result, brand);
    }

    @Test
    void getBrandById_test_ko(){
        // Given
        // When
        when(brandRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(BrandNotFoundException.class, () -> brandService.getBrandById(1));
        assertEquals("Brand with ID 1 not found.", exception.getMessage());
    }

    @Test
    void deleteBrand_test() {
        // Given
        int id = 1;
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(id);
        brandEntity.setName("BrandName");

        // When
        when(brandRepository.findById(id)).thenReturn(Optional.of(brandEntity));
        brandRepository.deleteById(id);

        // Then
        assertDoesNotThrow(() -> brandService.deleteBrand(id));
    }

    @Test
    void deleteBrand_test_ko() {
        // Given
        // When
        when(brandRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(BrandNotFoundException.class, () -> brandService.deleteBrand(1));
        assertEquals("Brand with ID 1 not found.", exception.getMessage());
    }

    @Test
    void getAllBrands_test() throws Exception {
        // Given
        List<BrandEntity> brandEntities = new ArrayList<>();
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        brandEntity.setName("Brand1");
        brandEntities.add(brandEntity);

        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Brand1");

        // When
        when(brandRepository.findAll()).thenReturn(brandEntities);
        when(brandConverter.toModel(brandEntity)).thenReturn(brand);

        // Then
        CompletableFuture<List<Brand>> future = brandService.getAllBrands();
        List<Brand> result = future.get();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Brand1", result.getFirst().getName());
    }

}
