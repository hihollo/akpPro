package com.xmm.akp.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    @Column(unique =true)
    private String name; //登陆名称
    private String password; //登陆密码

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
