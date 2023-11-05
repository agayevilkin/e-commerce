package com.example.elcstore.service.impl;

import com.example.elcstore.domain.HomepageWeeklyOffer;
import com.example.elcstore.domain.ProductOption;
import com.example.elcstore.dto.request.HomepageWeeklyOfferRequestDto;
import com.example.elcstore.dto.response.HomepageWeeklyOfferResponseDto;
import com.example.elcstore.dto.response.ProductOptionWeeklyOfferResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.HomepageWeeklyOfferRepository;
import com.example.elcstore.repository.ProductOptionRepository;
import com.example.elcstore.service.HomepageWeeklyOfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.NotFoundException.HOMEPAGE_WEEKLY_OFFER_NOT_FOUND;
import static com.example.elcstore.exception.NotFoundException.PRODUCT_OPTION_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class HomepageWeeklyOfferServiceImpl implements HomepageWeeklyOfferService {

    private final ModelMapper mapper;
    private final HomepageWeeklyOfferRepository homepageWeeklyOfferRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public void createHomepageWeeklyOffer(HomepageWeeklyOfferRequestDto requestDto) {
        HomepageWeeklyOffer homepageWeeklyOffer = mapper.map(requestDto, HomepageWeeklyOffer.class);
        homepageWeeklyOffer.setProductOptionId(getProductOptionById(requestDto.getProductOptionId()).getId());

        homepageWeeklyOfferRepository.save(homepageWeeklyOffer);
    }

    @Override
    @Transactional
    public HomepageWeeklyOfferResponseDto findById(UUID id) {
        HomepageWeeklyOffer homepageWeeklyOffer = getHomepageWeeklyOfferById(id);

        HomepageWeeklyOfferResponseDto homepageWeeklyOfferResponseDto =
                mapper.map(homepageWeeklyOffer, HomepageWeeklyOfferResponseDto.class);
        ProductOptionWeeklyOfferResponseDto productOptionWeeklyOfferResponseDto =
                mapper.map(getProductOptionById(homepageWeeklyOffer.getProductOptionId()), ProductOptionWeeklyOfferResponseDto.class);

        homepageWeeklyOfferResponseDto.setProductOption(productOptionWeeklyOfferResponseDto);
        return homepageWeeklyOfferResponseDto;
    }

    @Override
    @Transactional
    public List<HomepageWeeklyOfferResponseDto> getAllHomepageWeeklyOffers() {
        return homepageWeeklyOfferRepository.findAllByDeadlineAfter(LocalDateTime.now())
                .stream()
                .map((homepageWeeklyOffer -> {
                    HomepageWeeklyOfferResponseDto homepageWeeklyOfferResponseDto =
                            mapper.map(homepageWeeklyOffer, HomepageWeeklyOfferResponseDto.class);

                    ProductOptionWeeklyOfferResponseDto productOptionWeeklyOfferResponseDto =
                            mapper.map(getProductOptionById(homepageWeeklyOffer.getProductOptionId()), ProductOptionWeeklyOfferResponseDto.class);

                    homepageWeeklyOfferResponseDto.setProductOption(productOptionWeeklyOfferResponseDto);

                    return homepageWeeklyOfferResponseDto;
                }))
                .collect(Collectors.toList());
    }

    @Override
    public void updateHomepageWeeklyOffer(UUID id, HomepageWeeklyOfferRequestDto requestDto) {
        HomepageWeeklyOffer homepageWeeklyOffer = getHomepageWeeklyOfferById(id);
        mapper.map(requestDto, homepageWeeklyOffer);
        homepageWeeklyOffer.setProductOptionId(getProductOptionById(requestDto.getProductOptionId()).getId());

        homepageWeeklyOfferRepository.save(homepageWeeklyOffer);
    }

    @Override
    public void deleteHomepageWeeklyOffer(UUID id) {
        if (existsById(id)) {
            homepageWeeklyOfferRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return homepageWeeklyOfferRepository.existsById(id);
    }

    private ProductOption getProductOptionById(UUID id) {
        return productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_OPTION_NOT_FOUND));
    }

    private HomepageWeeklyOffer getHomepageWeeklyOfferById(UUID id) {
        return homepageWeeklyOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HOMEPAGE_WEEKLY_OFFER_NOT_FOUND));
    }
}
