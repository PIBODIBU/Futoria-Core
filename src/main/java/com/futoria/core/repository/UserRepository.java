package com.futoria.core.repository;

import com.futoria.core.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CoreUserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User getByUsername(String username);

    User getFirstById(Long id);
}
