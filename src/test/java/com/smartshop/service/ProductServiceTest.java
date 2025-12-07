package com.smartshop.service;

import com.smartshop.dto.Product.ProductResponseDTO;
import com.smartshop.entity.Product;
import com.smartshop.mapper.ProductMapper;
import com.smartshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void findByIdSuccess() {
        Product product = new Product();

        product.setId(1L);
        product.setDeleted(false);
        product.setName("product1");
        product.setPrice(99D);
        product.setStock(200);

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(1L);
        dto.setName("product1");
        dto.setPrice(99D);
        dto.setStock(200);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toResponseDTO(product)).thenReturn(dto);
        ProductResponseDTO findProduct= productService.findById(1L);

        assertNotNull(findProduct);
        assertEquals(1L,findProduct.getId());
    }

    @Test
    void findAllActive() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void softDelete() {
    }
}