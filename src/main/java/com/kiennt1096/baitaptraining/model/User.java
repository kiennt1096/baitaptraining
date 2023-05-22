package com.kiennt1096.baitaptraining.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(columnNames = "login_name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "login_name", length = 15, nullable = false)
    private String loginName;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "fullname", length = 255, nullable = false)
    private String fullName;

    @Column(name = "fullnamekana", length = 255)
    private String fullNameKana;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "tel", length = 15)
    private String tel;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

    @OneToOne
    @JoinColumn(name = "user_detail")
    private DetailUser detailUser;

    public User() {
    }

    public User(Integer user_id, Group group, String loginName, String password, String fullName, String fullNameKana, String email, String tel, Date birthday, DetailUser detailUser) {
        this.user_id = user_id;
        this.group = group;
        this.loginName = loginName;
        this.password = password;
        this.fullName = fullName;
        this.fullNameKana = fullNameKana;
        this.email = email;
        this.tel = tel;
        this.birthday = birthday;
        this.detailUser = detailUser;
    }

    public User(int i, Group group) {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameKana() {
        return fullNameKana;
    }

    public void setFullNameKana(String fullNameKana) {
        this.fullNameKana = fullNameKana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DetailUser getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(DetailUser detailUser) {
        System.out.println("Done setDetailUser");
        this.detailUser = detailUser;
    }
}
