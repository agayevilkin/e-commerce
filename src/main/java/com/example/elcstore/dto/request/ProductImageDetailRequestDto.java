package com.example.elcstore.dto.request;

import com.example.elcstore.domain.ProductOption;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
public class ProductImageDetailRequestDto {
    @NotNull
    private MultipartFile image;

    @NotNull
    private UUID productOptionId;

    @NotNull
    private int orderNum;
}
