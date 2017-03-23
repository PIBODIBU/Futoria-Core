package com.futoria.core.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "sys_rel_role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions;

    /*@OneToMany(mappedBy = "role", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RelUserRole> relUserRoles;

    @OneToMany(mappedBy = "role", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RelRolePermission> relRolePermissions;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

   /* public Set<RelUserRole> getRelUserRoles() {
        return relUserRoles;
    }

    public void setRelUserRoles(Set<RelUserRole> relUserRoles) {
        this.relUserRoles = relUserRoles;
    }

    public Set<RelRolePermission> getRelRolePermissions() {
        return relRolePermissions;
    }

    public void setRelRolePermissions(Set<RelRolePermission> relRolePermissions) {
        this.relRolePermissions = relRolePermissions;
    }*/
}
