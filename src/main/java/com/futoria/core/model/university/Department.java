package com.futoria.core.model.university;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_department")
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "department",
            orphanRemoval = true
    )
    private Set<Group> groups;

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
            name = "faculty_id"
    )
    private Faculty faculty;

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Subject> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
