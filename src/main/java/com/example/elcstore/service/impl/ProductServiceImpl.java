package com.example.elcstore.service.impl;

import com.example.elcstore.domain.*;
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
    private final ModelMapper mapper;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand not found!"));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        Product product = mapper.map(productRequestDto, Product.class);
        product.setBrand(brand);
        product.setCategory(category);
        product.setHighlight(getHiglightList(productRequestDto.getHighlight()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristics()));
        productRepository.save(product);
    }


    @Override
    public void updateProduct(ProductRequestDto productRequestDto, UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Computer Product"));
        mapper.map(productRequestDto, product);

        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand not found!"));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        product.setBrand(brand);
        product.setCategory(category);
        product.setHighlight(getHiglightList(productRequestDto.getHighlight()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristics()));
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

    @Override
    public void deleteProduct(UUID id) {
        if (existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

    private List<TechnicalCharacteristic> getTechnicalCharacteristicsList(List<UUID> technicalCharacteristics) {
        return null;
    }

    private List<Highlight> getHiglightList(List<UUID> highlight) {
        return null;
    }
}
