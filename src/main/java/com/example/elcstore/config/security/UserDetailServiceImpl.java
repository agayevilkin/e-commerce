package com.example.elcstore.config.security;

import com.example.elcstore.domain.SystemUser;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final SystemUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User Not Found."));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(SystemUser user,
                                            Collection<GrantedAuthority> authorities) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new MyUserPrincipal(user.getId(), username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

}