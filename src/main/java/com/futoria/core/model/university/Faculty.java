package com.futoria.core.model.university;

import com.futoria.core.model.UserData;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_faculty")
public class Faculty {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "faculty",
            orphanRemoval = true
    )
    private Set<UserData> usersData;

    @Column(
            name = "short_name",
            length = 50
    )
    private String shortName;

    @Column(
            name = "long_name",
            length = 100
    )
    private String longName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
