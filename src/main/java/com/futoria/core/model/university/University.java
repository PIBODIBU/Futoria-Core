package com.futoria.core.model.university;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_university")
public class University {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "university",
            orphanRemoval = true
    )
    private Set<Faculty> faculties;

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

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
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
