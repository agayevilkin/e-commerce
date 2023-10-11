package com.example.elcstore.dto.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class OAuth2UserInfoDto {
    private String id;
    private String name;
    private String email;
    private String picture;
}
