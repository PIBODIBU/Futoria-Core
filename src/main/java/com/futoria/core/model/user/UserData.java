package com.futoria.core.model.user;

import com.futoria.core.model.university.Group;

import javax.persistence.*;

@Entity
@Table(name = "sys_user_data")
public class UserData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    @Column(
            name = "book_num",
            length = 20
    )
    private String bookNum;

    @Column(
            name = "phone",
            length = 20
    )
    private String phone;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "group_id"
    )
    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
