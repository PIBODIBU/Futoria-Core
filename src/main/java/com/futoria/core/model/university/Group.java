package com.futoria.core.model.university;

import com.futoria.core.model.user.User;
import com.futoria.core.model.user.UserData;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_university_group")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "group",
            orphanRemoval = true
    )
    private Set<UserData> usersData;

    @Column(
            name = "name",
            length = 100
    )
    private String name;

    @Column(
            name = "sub_group"
    )
    private Integer subGroup;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "department_id"
    )
    private Department department;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "master_id"
    )
    private User headMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserData> getUsersData() {
        return usersData;
    }

    public void setUsersData(Set<UserData> usersData) {
        this.usersData = usersData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(Integer subGroup) {
        this.subGroup = subGroup;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getHeadMaster() {
        return headMaster;
    }

    public void setHeadMaster(User headMaster) {
        this.headMaster = headMaster;
    }
}
