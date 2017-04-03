package com.futoria.core.service;

import com.futoria.core.application.configuration.security.FutoriaSecurityService;
import com.futoria.core.model.security.Permission;
import com.futoria.core.model.security.Role;
import com.futoria.core.model.user.User;
import com.futoria.core.repository.PermissionRepository;
import com.futoria.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("CorePermissionService")
@Transactional(value = "txManager", readOnly = true)
public class PermissionService {
    private PermissionRepository permissionRepository;
    private UserRepository userRepository;
    private FutoriaSecurityService securityService;

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Permission get(Long id) {
        return permissionRepository.findFirstById(id);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Set<Permission> get(String name) {
        return permissionRepository.getAllByName(name);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_CREATE')")
    @Transactional
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Set<Permission> getMyPermissions() {
        Set<Permission> permissions = new HashSet<>();
        User user = userRepository.getFirstById(securityService.getUserFromContext().getId());

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return permissions;
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

    @Autowired
    public void setPermissionRepository(@Qualifier("CorePermissionRepository")
                                                PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Autowired
    public void setUserRepository(@Qualifier("CoreUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSecurityService(@Qualifier("CoreSecurityService") FutoriaSecurityService securityService) {
        this.securityService = securityService;
    }
}
