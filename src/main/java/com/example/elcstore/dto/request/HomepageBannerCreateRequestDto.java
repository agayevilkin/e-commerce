package com.example.elcstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class HomepageBannerCreateRequestDto {

    @NotNull(message = "File cannot be null")
    private MultipartFile file;

    @Size(max = 255, message = "Link must be less than or equal to 255 characters")
    private String link;

    @NotNull(message = "Order number cannot be null")
    @Min(value = 1, message = "Order number must be greater than or equal to 1")
    private int orderNum;
}
