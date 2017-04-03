package com.futoria.core.application.configuration.security;

import com.futoria.core.model.user.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FutoriaUserDetails extends org.springframework.security.core.userdetails.User {
    private User user;

    public FutoriaUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(),
                user.getPassword(),
                authorities);
        this.user = user;
    }

    public FutoriaUserDetails(User user,
                              boolean enabled,
                              boolean accountNonExpired,
                              boolean credentialsNonExpired,
                              boolean accountNonLocked,
                              Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}