package com.instagram.instagram.config.security;

import com.instagram.instagram.domains.auth.AuthUser;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final AuthUser authUser;
    private final Long id;
    private final String email;

    public UserDetails(@NonNull AuthUser authUser) {
        this.authUser = authUser;
        this.email = authUser.getEmail();
        this.id = authUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + authUser.getRole()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getActive().equals(AuthUser.Active.ACTIVE);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
