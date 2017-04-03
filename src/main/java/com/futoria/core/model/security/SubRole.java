package com.futoria.core.model.security;

import com.futoria.core.model.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_sub_role")
public class SubRole {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "subRoles", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<User> users;
}
