package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class HomepageBannerResponseDto {

    private UUID id;
    private UUID imageId;
    private String link;
    private int orderNum;
}
