package com.example.elcstore.service.impl;

import com.example.elcstore.domain.*;
import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.request.ProductRequestDto;
import com.example.elcstore.dto.response.ProductDetailedResponseDto;
import com.example.elcstore.dto.response.ProductPreviewResponseDto;
import com.example.elcstore.dto.search.ProductSearchCriteriaDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.*;
import com.example.elcstore.service.ProductService;
import com.example.elcstore.service.search.ProductSearchSpecification;
import jakarta.transaction.Transactional;
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
    private final ProductSearchSpecification searchSpecification;
    private final HighlightRepository highlightRepository;
    private final TechnicalCharacteristicRepository technicalCharacteristicRepository;
    private final BrandRepository brandRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = mapper.map(productRequestDto, Product.class);
        product.setBrand(getBrandById(productRequestDto.getBrandId()));
        product.setCategories(getCategoryList(productRequestDto.getCategoryIds()));
        if (product.getHighlight() != null) product.setHighlight(getHighlightById(productRequestDto.getHighlightId()));
        product.setEvents(getEventList(productRequestDto.getEventIds()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristicsIds()));

        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto, UUID id) {
        Product product = getProductById(id);
        mapper.map(productRequestDto, product);
        product.setBrand(getBrandById(productRequestDto.getBrandId()));
        if (product.getHighlight() != null) product.setHighlight(getHighlightById(productRequestDto.getHighlightId()));
        product.setCategories(getCategoryList(productRequestDto.getCategoryIds()));
        product.setEvents(getEventList(productRequestDto.getEventIds()));
        product.setTechnicalCharacteristic(getTechnicalCharacteristicsList(productRequestDto.getTechnicalCharacteristicsIds()));

        productRepository.save(product);
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProducts(Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByStatusIsTrue(PageRequest.of(pageIndex, pageSize))
                .map((product -> mapper.map(product, ProductPreviewResponseDto.class))));
    }

    @Override
    @Transactional
    public ProductDetailedResponseDto findById(UUID id) {
        return mapper.map(getProductById(id), ProductDetailedResponseDto.class);
    }

    @Override
    public ProductDetailedResponseDto findByProductIdentificationNameAndHighlight(String idName, String highlight) {
        Product product = productRepository.findByHighlight_ProductIdentificationNameAndHighlight_ValueAndStatusIsTrue(idName, highlight).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        return mapper.map(product, ProductDetailedResponseDto.class);
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllNewProducts(Integer pageIndex, Integer pageSize) {
        // TODO: 9/18/2023 can be change time
        LocalDateTime yesterday = LocalDateTime.now().minusDays(30);
        return new CustomPage<>(productRepository.findAllByCreatedDateAfterAndStatusIsTrue(yesterday, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllDiscountedProducts(Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByDiscountIsNotNullAndStatusIsTrue(PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProductsByCategoryId(UUID categoryId, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByCategoriesIdAndStatusIsTrue(categoryId, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> getAllProductsByBrandId(UUID brandId, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAllByBrandIdAndStatusIsTrue(brandId, PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));

    }

    // TODO: 10/15/2023 get all product by category List (change getAllProductByCategory or add new method)
    @Override
    public CustomPage<ProductPreviewResponseDto> filteredSearchProduct(ProductSearchCriteriaDto productSearchCriteriaDto, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAll(new ProductSearchSpecification(productSearchCriteriaDto.getCriteriaList()), PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class)));
    }

    @Override
    public CustomPage<ProductPreviewResponseDto> searchProduct(String query, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productRepository.findAll(searchSpecification.buildSpecificaationForQuerySearch(query), PageRequest.of(pageIndex, pageSize))
                .map(product -> mapper.map(product, ProductPreviewResponseDto.class))
        );
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

    private List<Category> getCategoryList(List<UUID> categories) {
        return categories
                .stream()
                .map((e) -> categoryRepository
                        .findById(e)
                        .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage())))
                .collect(Collectors.toList());
    }

    private Brand getBrandById(UUID id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND.getMessage()));
    }

    private Highlight getHighlightById(UUID id) {
        return highlightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HIGHLIGHT_NOT_FOUND.getMessage()));
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND.getMessage()));
    }
}
