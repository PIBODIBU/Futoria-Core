package com.futoria.core.repository;

import com.futoria.core.model.security.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("CorePermissionRepository")
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Permission findFirstById(Long id);

    Set<Permission> getAllByName(String name);
}
