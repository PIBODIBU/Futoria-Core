package com.futoria.core.application.configuration.security;

import com.futoria.core.model.Permission;
import com.futoria.core.model.Role;
import com.futoria.core.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityService {
    public boolean hasPermission(String permission) {
        CustomUserDetails userDetails = ((CustomUserDetails) SecurityContextHolder
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

}

