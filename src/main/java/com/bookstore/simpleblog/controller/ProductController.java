package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.ProductDto;
import com.bookstore.simpleblog.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ProductDto createProduct(@ModelAttribute ProductDto productDto,
//                                    @RequestPart("image") MultipartFile image,
                                    @RequestPart("category_id") Long category_id
                                    ) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);

        return productService.createProduct(productDto,category_id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id,@RequestPart("data") String productDtoJson,
                              @RequestPart("image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);

        productService.updateProduct(id,productDto,image);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
