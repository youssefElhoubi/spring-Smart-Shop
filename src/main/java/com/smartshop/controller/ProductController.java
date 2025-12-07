package com.smartshop.controller;

import com.smartshop.dto.Product.ProductRequestDTO;
import com.smartshop.dto.Product.ProductResponseDTO;
import com.smartshop.service.ProductService;
import com.smartshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProduct(@PathVariable Long id) {
        ProductResponseDTO responseDTO = productService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> all(){
        List<ProductResponseDTO> products = productService.findAllActive();
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(@Valid ProductRequestDTO dto){
        ProductResponseDTO responseDTO = productService.create(dto);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.softDelete(id);
        return ResponseEntity.ok("product deleted ");

    }
}