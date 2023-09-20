package com.example.elcstore.service.impl;

import com.example.elcstore.domain.*;
import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.*;
import com.example.elcstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HighlightRepository highlightRepository;
    private final TechnicalCharacteristicRepository technicalCharacteristicRepository;
    private final BrandRepository brandRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));
        Highlight highlight = highlightRepository.findById(productRequestDto.getHighlightId())
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_NOT_FOUND.getMessage()));

        Product product = mapper.map(productRequestDto, Product.class);
        product.setBrand(brand);
        product.setCategory(category);
        product.setHighlight(highlight);
        product.setEvents(getEventList(productRequestDto.getEvents()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristics()));

        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto, UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        mapper.map(productRequestDto, product);

        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));

        product.setBrand(brand);
        product.setCategory(category);
        product.setEvents(getEventList(productRequestDto.getEvents()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristics()));
        productRepository.save(product);
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProducts(Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAll(PageRequest.of(pageIndex, pageSize))
                .map((product -> mapper.map(product, ProductPreviewResponseDto.class))));
    }

    @Override
    public ProductDetailedResponseDto findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        return mapper.map(product, ProductDetailedResponseDto.class);
    }

    @Override
    public ProductDetailedResponseDto findByProductIdentificationNameAndHighlight(String idName, String highlight) {
        Product product = productRepository.findByHighlight_ProductIdentificationNameAndHighlight_Value(idName, highlight).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        return mapper.map(product, ProductDetailedResponseDto.class);
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllNewProducts(Integer pageIndex, Integer pageSize) {
        // TODO: 9/18/2023 can be change time
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return new CustomPage<>(productRepository.findAllByCreatedDateAfter(yesterday, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllDiscountedProducts(Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByDiscountIsNotNull(PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProductsByCategory(String category, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByCategory_Name(category, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProductsByCategoryAndBrand(String category, String brand, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByCategory_NameAndBrand_Name(category, brand, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
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
        return technicalCharacteristics
                .stream()
                .map((tc) -> technicalCharacteristicRepository
                        .findById(tc)
                        .orElseThrow(() -> new NotFoundException(TECHNICAL_CHARACTERISTIC_NOT_FOUND.getMessage())))
                .collect(Collectors.toList());
    }

    private List<Event> getEventList(List<UUID> events) {
        return events
                .stream()
                .map((e) -> eventRepository
                        .findById(e)
                        .orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND.getMessage())))
                .collect(Collectors.toList());
    }

}
