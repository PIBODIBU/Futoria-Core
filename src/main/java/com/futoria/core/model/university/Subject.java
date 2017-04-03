package com.futoria.core.model.university;

import javax.persistence.*;

@Entity
@Table(name = "sys_subject")
@Inheritance
public class Subject {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(
            name = "uuid",
            length = 36,
            nullable = false,
            unique = true
    )
    private String uuid;

    @Column(
            name = "long_name",
            nullable = false,
            length = 100
    )
    private String longName;

    @Column(
            name = "short_name",
            nullable = false,
            length = 50
    )
    private String shortName;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
