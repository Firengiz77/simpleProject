package com.bookstore.simpleblog.mapper;

import com.bookstore.simpleblog.dto.ProductDto;
import com.bookstore.simpleblog.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)

public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toDto(List<Product> productList);
    List<Product> toEntity(List<ProductDto> productDtoList);
}
