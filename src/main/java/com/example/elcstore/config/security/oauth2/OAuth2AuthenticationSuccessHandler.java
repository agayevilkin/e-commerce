package com.example.elcstore.config.security.oauth2;

import com.example.elcstore.config.security.GenericUserPrincipal;
import com.example.elcstore.config.security.JwtService;
import com.example.elcstore.dto.auth.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        String targetUrl = determineTargetUrl(request, response, authentication);
//
//        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
//            return;
//        }
//
//        clearAuthenticationAttributes(request, response);
//        try {
//            getRedirectStrategy().sendRedirect(request, response, targetUrl);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        GenericUserPrincipal oAuth2UserPrincipal = GenericUserPrincipal
                .builder()
                .authorities(userPrincipal.getAuthorities())
                .userId(userPrincipal.getId())
                .username(userPrincipal.getUsername())
                .build();
        AuthResponse authResponse = jwtService.generateToken(oAuth2UserPrincipal);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(authResponse);

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }
//    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
//                .map(Cookie::getValue);
//
//        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//        }
//
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//
//        String token = tokenProvider.createToken(authentication);
//
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .build().toUriString();
//    }

//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }
//
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//
//        return appProperties.getOauth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    // Only validate host and port. Let the clients use different paths if they want to
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//                        return true;
//                    }
//                    return false;
//                });
//    }
}