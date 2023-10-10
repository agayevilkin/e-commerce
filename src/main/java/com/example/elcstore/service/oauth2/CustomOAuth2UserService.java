package com.example.elcstore.service.oauth2;

import com.example.elcstore.domain.Customer;
import com.example.elcstore.domain.Role;
import com.example.elcstore.domain.User;
import com.example.elcstore.domain.enums.AuthProvider;
import com.example.elcstore.domain.enums.UserAccountStateStatus;
import com.example.elcstore.domain.enums.UserStatus;
import com.example.elcstore.dto.oauth2.OAuth2UserInfoDto;
import com.example.elcstore.config.security.oauth2.UserPrincipal;
import com.example.elcstore.exception.OAuth2AuthenticationProcessingException;
import com.example.elcstore.repository.UserRepository;
import com.example.elcstore.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfoDto userInfoDto = OAuth2UserInfoDto
                .builder()
                .name(oAuth2User.getAttributes().get("name").toString())
                .id(oAuth2User.getAttributes().get("sub").toString())
                .email(oAuth2User.getAttributes().get("email").toString())
                .picture(oAuth2User.getAttributes().get("picture").toString())
                .build();

        log.trace("User info is {}", userInfoDto);
        Optional<User> userOptional = userRepository.findByUsername(userInfoDto.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getAuthProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                // TODO: 10/10/2023 Fix the exception message or if looks good then don't touch it
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getAuthProvider() + " account. Please use your " + user.getAuthProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, userInfoDto);
        } else {
            user = registerNewUser(oAuth2UserRequest, userInfoDto);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }


    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfoDto oAuth2UserInfoDto) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(oAuth2UserInfoDto.getEmail());
        user.setRoles(getDefaultRole());
        user.setUserStatus(UserStatus.CUSTOMER);
        // TODO: 10/9/2023 can be change because not exist email and can be add email verification
        user.setUserAccountStateStatus(UserAccountStateStatus.ACTIVE);
        user.setAuthProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfoDto.getId());
        Customer customer = new Customer();
        customer.setProfilePic(oAuth2UserInfoDto.getPicture());
        user.setCustomer(customer);
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfoDto oAuth2UserInfoDto) {
        existingUser.setUsername(oAuth2UserInfoDto.getEmail());
        existingUser.getCustomer().setProfilePic(oAuth2UserInfoDto.getPicture());
        return userRepository.save(existingUser);
    }

    private List<Role> getDefaultRole() {
        return Collections.singletonList(roleService.getDefaultRole());
    }
}
