package com.futoria.core.model.university;

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
    private Set<Department> departments;

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

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "university_id"
    )
    private University university;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
