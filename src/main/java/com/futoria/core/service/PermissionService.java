package com.futoria.core.service;

import com.futoria.core.model.Permission;
import com.futoria.core.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("CorePermissionService")
@Transactional(value = "txManager", readOnly = true)
public class PermissionService {
    private PermissionRepository permissionRepository;

    @Autowired
    public void setPermissionRepository(@Qualifier("CorePermissionRepository")
                                                PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

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
}
