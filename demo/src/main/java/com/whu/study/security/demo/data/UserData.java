package com.whu.study.security.demo.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
@Table(name="nms_user")
public class UserData implements Serializable {

    @Id
    private String userId = "";
    @Column
    private String userName = "";
    @Column
    private String password = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserData.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .toString();
    }

    public Collection<Object> getRoleIdList() {
        return new ArrayList<>();
    }
}
