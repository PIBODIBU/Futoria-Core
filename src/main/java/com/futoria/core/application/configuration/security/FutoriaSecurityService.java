package com.futoria.core.application.configuration.security;

import com.futoria.core.model.Permission;
import com.futoria.core.model.Role;
import com.futoria.core.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component("CoreSecurityService")
public class FutoriaSecurityService {
    public boolean hasPermission(String permission) {
        FutoriaUserDetails userDetails = ((FutoriaUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());

        User user = userDetails.getUser();

        for (Role role : user.getRoles()) {
            for (Permission rolePermission : role.getPermissions()) {
                if (rolePermission.getName().equals(permission))
                    return true;
            }
        }

        return false;
    }

    /*public boolean hasRoleAsMajor(String role) {
        FutoriaUserDetails userDetails = ((FutoriaUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());

        User user = userDetails.getUser();

        Collection<GrantedAuthority> roles = userDetails.getAuthorities();

        Collections.sort(roles, (t, t1) -> ((GrantedAuthority) t).getAuthority());

        return false;
    }*/

    public User getUserFromContext() {
        return ((FutoriaUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

    // Beans
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

