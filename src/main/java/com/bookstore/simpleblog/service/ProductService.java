package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.ProductDto;
import com.bookstore.simpleblog.exceptions.CategoryNotFoundException;
import com.bookstore.simpleblog.exceptions.ProductNotFoundException;
import com.bookstore.simpleblog.mapper.ProductMapper;
import com.bookstore.simpleblog.model.Product;
import com.bookstore.simpleblog.repository.CategoryRepository;
import com.bookstore.simpleblog.repository.ImageRepository;
import com.bookstore.simpleblog.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository, ImageService imageService, CategoryRepository categoryRepository, ImageRepository imageRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public List<ProductDto> getProducts() {
        return productMapper.toDto(productRepository.findAll());
    }

    public ProductDto getProduct(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id)));
    }

    public ProductDto createProduct(ProductDto productDto, Long category_id) {
       // List<Image> image1 = Collections.singletonList(imageService.create(image));
        Product productToEntity = productMapper.toEntity(productDto);

       Product product = Product.builder()
               .name(productToEntity.getName())
               .description(productToEntity.getDescription())
               .price(productToEntity.getPrice())
               .quantity(productToEntity.getQuantity())
               .category(categoryRepository.findById(category_id).orElseThrow(()-> new CategoryNotFoundException(category_id)))
              // .image(image1)
               .build();
        return productMapper.toDto(productRepository.save(product));
    }

    public void updateProduct(Long id, ProductDto productDto, MultipartFile image) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        Product productToEntity = productMapper.toEntity(productDto);
        product.setName(productToEntity.getName());
        product.setDescription(productToEntity.getDescription());
        product.setPrice(productToEntity.getPrice());
        product.setQuantity(productToEntity.getQuantity());
        product.setCategory(productToEntity.getCategory());
      //  product.setImage(Collections.singletonList(imageService.update(product.getImage().getFirst().getId(), image)));
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
