package com.smartshop.service;

import com.smartshop.dto.Product.ProductRequestDTO;
import com.smartshop.dto.Product.ProductResponseDTO;
import com.smartshop.entity.Product;
import com.smartshop.exeptions.ResourceNotFoundException;
import com.smartshop.mapper.ProductMapper;
import com.smartshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return productMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> findAllActive() {
        List<Product> activeProducts = productRepository.findByDeletedFalse();
        return activeProducts.stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product newProduct = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toResponseDTO(savedProduct);
    }
}
