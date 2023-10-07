package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Credit;
import com.example.elcstore.domain.Product;
import com.example.elcstore.dto.request.CreditRequestDto;
import com.example.elcstore.dto.response.CreditResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CreditRepository;
import com.example.elcstore.repository.ProductRepository;
import com.example.elcstore.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void createCredit(CreditRequestDto requestDto) {
        Product product = getProductById(requestDto.getProductId());
        Credit credit = mapper.map(requestDto, Credit.class);
        credit.setProduct(product);
        double monthlyPaymentAmount = getMonthlyPaymentAmount(requestDto, product.getPrice());
        credit.setMonthlyPaymentAmount(monthlyPaymentAmount);
        credit.setCurrentPrice(getCurrentProductPrice(monthlyPaymentAmount, requestDto.getCreditPeriod()));
        creditRepository.save(credit);
    }

    @Override
    public CreditResponseDto findById(UUID id) {
        return mapper.map(getCreditById(id), CreditResponseDto.class);
    }

    @Override
    public List<CreditResponseDto> getAllCreditOptionsByProductId(UUID productId) {
        return creditRepository.findAllByProduct_Id(productId)
                .stream()
                .map((credit) -> mapper.map(credit, CreditResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCredit(UUID id, CreditRequestDto requestDto) {
        Credit credit = getCreditById(id);
        Product product = getProductById(requestDto.getProductId());
        mapper.map(requestDto, credit);
        credit.setProduct(product);
        double monthlyPaymentAmount = getMonthlyPaymentAmount(requestDto, product.getPrice());
        credit.setMonthlyPaymentAmount(monthlyPaymentAmount);
        credit.setCurrentPrice(getCurrentProductPrice(monthlyPaymentAmount, requestDto.getCreditPeriod()));
        creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(UUID id) {
        if (existsById(id)) {
            creditRepository.deleteById(id);
        }
    }
    // TODO: 10/7/2023 add another service method for calculating
    //  the credit amount that the customer can afford with the option to pay an initial payment.

    private boolean existsById(UUID id) {
        return creditRepository.existsById(id);
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_NOT_FOUND));
    }

    private Credit getCreditById(UUID id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CREDIT_NOT_FOUND));

    }

    // TODO: 10/6/2023 can be change logic
    public static double getMonthlyPaymentAmount(CreditRequestDto requestDto, double productOriginalPrice) {
        // Extract values from the request DTO
        double interestRate = requestDto.getInterest();
        int creditPeriodMonths = requestDto.getCreditPeriod();
        double initialPaymentAmount = requestDto.getInitialPaymentAmount();

        // Calculate the product price after subtracting the initial payment
        double productPriceWithInitialPayment = productOriginalPrice - initialPaymentAmount;

        // Calculate the total interest as a monetary value
        double totalInterestAmount = (interestRate * productPriceWithInitialPayment) / 100.0;

        // Convert the relevant values to BigDecimal for precise arithmetic
        BigDecimal productPrice = BigDecimal.valueOf(productPriceWithInitialPayment);
        BigDecimal totalInterest = BigDecimal.valueOf(totalInterestAmount);
        BigDecimal creditPeriod = BigDecimal.valueOf(creditPeriodMonths);

        // Perform the calculation using BigDecimal
        BigDecimal finalResult = productPrice.add(totalInterest);
        BigDecimal monthlyPayment = finalResult.divide(creditPeriod, RoundingMode.HALF_UP);

        return monthlyPayment.doubleValue();
    }

    private double getCurrentProductPrice(double monthlyPaymentAmount, int creditPeriod) {
        return monthlyPaymentAmount * creditPeriod;
    }
}
