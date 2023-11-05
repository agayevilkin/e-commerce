package com.example.elcstore.config.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Builder
@Setter
@Getter
public class GenericUserPrincipal {

    private String username;
    private UUID userId;
    private Collection<? extends GrantedAuthority> authorities;
}
