package com.example.elcstore.service.impl;

import com.example.elcstore.domain.*;
import com.example.elcstore.domain.enums.SearchOperation;
import com.example.elcstore.domain.enums.StockStatus;
import com.example.elcstore.domain.pagination.CustomPage;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.request.ProductOptionCreateRequestDto;
import com.example.elcstore.dto.request.ProductOptionImageDetailRequestDto;
import com.example.elcstore.dto.request.ProductOptionUpdateRequestDto;
import com.example.elcstore.dto.response.ProductOptionAdminPreviewResponseDto;
import com.example.elcstore.dto.response.ProductOptionCategoryBannerResponseDto;
import com.example.elcstore.dto.response.ProductOptionDetailedResponseDto;
import com.example.elcstore.dto.response.ProductOptionRealTimeSearchResponseDto;
import com.example.elcstore.dto.search.SearchCriteria;
import com.example.elcstore.exception.ImageUploadException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.*;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.service.ProductOptionService;
import com.example.elcstore.service.search.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.ImageUploadException.FAILED_UPLOAD_IMAGE;
import static com.example.elcstore.exception.NotFoundException.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final static int MAX_HEIGHT = 315;
    private final static int MAX_WIDTH = 315;

    private final ProductOptionRepository productOptionRepository;
    private final CreditRepository creditRepository;
    private final HomepageWeeklyOfferRepository homepageWeeklyOfferRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void createProductOption(ProductOptionCreateRequestDto requestDto) {
        ProductOption productOption = mapper.map(requestDto, ProductOption.class);
        productOption.setThumbnailId(imageService.uploadImageWithByteArray(resizeImage(requestDto.getThumbnail())).getId());
        //ProductOptionImageDetailRequestDto list wait test
        productOption.setImageDetails(getImageDetailList(requestDto.getImageDetailRequestDtoList(), productOption));
        productOption.setProduct(getProductById(requestDto.getProductId()));
        productOption.setColor(getColorById(requestDto.getColorId()));

        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public ProductOptionDetailedResponseDto findById(UUID id) {
        return mapper.map(getProductOptionById(id), ProductOptionDetailedResponseDto.class);
    }

    @Override
    public List<ProductOptionAdminPreviewResponseDto> findAllByProductId(UUID id) {
        return productOptionRepository.findAllByProduct_Id(id)
                .stream()
                .map((productOption -> mapper.map(productOption, ProductOptionAdminPreviewResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProductOption(UUID id, ProductOptionUpdateRequestDto requestDto) {
        ProductOption productOption = getProductOptionById(id);
        mapper.map(requestDto, productOption);
        productOption.setProduct(getProductById(requestDto.getProductId()));
        productOption.setColor(getColorById(requestDto.getColorId()));
//        ProductOptionImageDetailRequestDto list wait test
        productOption.getImageDetails().addAll(getImageDetailList(requestDto.getImageDetailRequestDtoList(), productOption));

        productOptionRepository.save(productOption);
    }

    @Override
    @Transactional
    public void updateThumbnail(UUID id, MultipartFile file) {
        ProductOption productOption = getProductOptionById(id);
        productOption.setThumbnailId(imageService.updateImageWithByteArray(resizeImage(file), productOption.getThumbnailId()).getId());
        productOptionRepository.save(productOption);
    }

    @Override
    public void updateStockStatus(UUID id, StockStatus stockStatus) {
        ProductOption productOption = getProductOptionById(id);
        productOption.setStockStatus(stockStatus);
        productOptionRepository.save(productOption);
    }

    @Override
    public List<ProductOptionRealTimeSearchResponseDto> searchProductOptionRealTime(String query) {
        SearchCriteria criteria = SearchCriteria.builder().key("title").searchOperation(SearchOperation.LIKE).value(query).build();
        return productOptionRepository.findAll(new GenericSearchSpecification<>(criteria), PageRequest.of(0, 10))
                .stream()
                .map((productOption) -> mapper.map(productOption, ProductOptionRealTimeSearchResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomPage<ProductOptionCategoryBannerResponseDto> findAllByCategoryId(UUID categoryId, Integer pageIndex, Integer pageSize) {
        return new CustomPage<>(productOptionRepository.findAllByProduct_Categories_Id(categoryId, PageRequest.of(pageIndex, pageSize))
                .map((productOption -> {
                    UUID productId = productOption.getProduct().getId();
                    ProductOptionCategoryBannerResponseDto responseDto = mapper.map(productOption, ProductOptionCategoryBannerResponseDto.class);
                    responseDto.setProductId(productId);
                    responseDto.setBrandImageId(productOption.getProduct().getBrand().getImageId());
                    Credit credit = creditRepository.findByProduct_Id(productId);
                    if (credit != null) {
                        responseDto.setMonthlyPrice(credit.getMonthlyPaymentAmount());
                    }
                    return responseDto;
                })));
    }

    @Override
    @Transactional
    public void deleteProductOption(UUID id) {
        ProductOption productOption = getProductOptionById(id);
        List<HomepageWeeklyOffer> homepageWeeklyOffers = getAllHomepageWeeklyOffersByProductOptionId(id);
        homepageWeeklyOffers.forEach(offer -> {
            homepageWeeklyOfferRepository.deleteById(offer.getId());
        });
        imageService.deleteImage(productOption.getThumbnailId());
        productOptionRepository.delete(productOption);
    }

    private List<HomepageWeeklyOffer> getAllHomepageWeeklyOffersByProductOptionId(UUID id) {
        return homepageWeeklyOfferRepository.findAllByProductOptionId(id);
    }

    // TODO: 9/20/2023 this method is work according to
    //  me but can be not work as not test by Multipart File type
    private List<ProductImageDetail> getImageDetailList(List<ProductOptionImageDetailRequestDto> productImageDetailRequestDto, ProductOption productOption) {
        if (productImageDetailRequestDto != null && !productImageDetailRequestDto.isEmpty()) {
            return productImageDetailRequestDto
                    .stream()
                    .map((imageDetail) -> {
                        ProductImageDetail productImageDetail = mapper.map(imageDetail, ProductImageDetail.class);
                        productImageDetail.setProductOption(productOption);
                        return productImageDetail;
                    })
                    .collect(Collectors.toList());
        } else return Collections.emptyList();
    }

    private byte[] resizeImage(MultipartFile image) {
        try {
            ImageInfoDto info = imageService.getImageWidthAndHeight(image.getBytes());
            if (info.getHeight() > MAX_HEIGHT && info.getWidth() > MAX_WIDTH) {
                return imageService.resizeImage(image.getBytes(), MAX_WIDTH, MAX_HEIGHT);
            } else
                return imageService.resizeImage(image.getBytes(), info.getWidth(), info.getHeight());
        } catch (IOException e) {
            log.trace(FAILED_UPLOAD_IMAGE, e);
            throw new ImageUploadException(e.getMessage());
        }
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    private Color getColorById(UUID id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COLOR_NOT_FOUND));
    }

    private ProductOption getProductOptionById(UUID id) {
        return productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
    }
}
