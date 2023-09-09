package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Brand;
import com.example.elcstore.domain.Category;
import com.example.elcstore.domain.Color;
import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.enums.StockStatus;
import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.request.ProductRequestWithCategoryAndBrandDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.*;
import com.example.elcstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final ModelMapper mapper;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Color color = colorRepository.findById(productRequestDto.getColorId())
                .orElseThrow(() -> new NotFoundException("Color not found!"));
        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand not found!"));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        Product product = mapper.map(productRequestDto, Product.class);
        product.setBrand(brand);
        product.setCategory(category);
        product.setStockStatus(StockStatus.IN_STOCK);
        product.setColor(color);
        product.setCreatedDate(LocalDateTime.now());
//        product.setCreatedBy("userinfo");
        //todo set fields which must be auto create
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto, UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(productRequestDto, product);

        //todo can be change logic for all if block
        if (product.getColor() == null && !(checkColorId(productRequestDto.getColorId())
                && product.getColor().getId().equals(productRequestDto.getColorId()))) {
            System.out.println("color in the if block");
            Color color = colorRepository.findById(productRequestDto.getColorId())
                    .orElseThrow(() -> new NotFoundException("Color not found!"));
            product.setColor(color);
        }

        if (product.getBrand() != null && !(checkBrandId(productRequestDto.getBrandId()) && product.getBrand().getId().equals(productRequestDto.getBrandId()))) {
            System.out.println("brand in the if block");
            Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                    .orElseThrow(() -> new NotFoundException("Brand not found!"));
            product.setBrand(brand);
        }

        if (product.getCategory() != null && !(checkCategoryId(productRequestDto.getCategoryId()) && product.getCategory().getId().equals(productRequestDto.getCategoryId()))) {
            System.out.println("category in the if block");
            Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found!"));
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    @Override
    public List<ProductPreviewResponseDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailedResponseDto findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product"));
        return mapper.map(product, ProductDetailedResponseDto.class);
    }

    @Override
    public void deleteProduct(UUID id) {
        if (existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<ProductPreviewResponseDto> getAllNewProduct() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return productRepository.findAllByCreatedDateAfter(yesterday)
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPreviewResponseDto> getAllDiscountedProduct() {
        return productRepository.findAllByDiscountIsNotNull()
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPreviewResponseDto> getAllProductByCategory(String category) {
        return productRepository.findAllByCategory_Name(category)
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPreviewResponseDto> getAllProductByCategoryAndBrand(ProductRequestWithCategoryAndBrandDto request) {
        return productRepository.findAllByCategory_NameAndBrand_Name(request.getCategory(), request.getBrand())
                .stream()
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
                .collect(Collectors.toList());
    }

    private boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

    private boolean checkCategoryId(UUID brandId) {
        return categoryRepository.existsById(brandId);
    }

    private boolean checkBrandId(UUID brandId) {
        return brandRepository.existsById(brandId);
    }

    private boolean checkColorId(UUID colorId) {
        return colorRepository.existsById(colorId);
    }
}
