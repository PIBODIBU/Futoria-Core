package com.futoria.core.application.configuration.security;

import com.futoria.core.model.security.Permission;
import com.futoria.core.model.security.Role;
import com.futoria.core.model.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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

    public User getUserFromContext() {
        return ((FutoriaUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }
}

