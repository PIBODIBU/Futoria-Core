package com.futoria.core.service;

import com.futoria.core.application.configuration.security.CustomUserDetails;
import com.futoria.core.model.Permission;
import com.futoria.core.model.Role;
import com.futoria.core.model.User;
import com.futoria.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("CoreUserService")
@Transactional(value = "txManager", readOnly = true)
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(@Qualifier("CoreUserRepository")
                                          UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_PROFILE_USER_READ')")
    public User getUser(Long id) {
        return userRepository.getFirstById(id);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Set<Permission> getUserPermissions(Long userId) {
        Set<Permission> permissions = new HashSet<>();
        User user = userRepository.getFirstById(userId);

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return permissions;
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Set<Permission> getMyPermissions() {
        Set<Permission> permissions = new HashSet<>();
        User user = userRepository.getFirstById(
                ((CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUser()
                        .getId());

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return permissions;
    }
    
    public User getMyData() {
        return userRepository.getFirstById(
                ((CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUser()
                        .getId());
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_CREATE')")
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }
}