package com.futoria.core.repository;

import com.futoria.core.model.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("CoreRoleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findFirstById(Long id);

    Set<Role> getAllByRoleName(String roleName);
}
