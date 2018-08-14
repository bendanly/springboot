package com.study;

import java.io.Serializable;

/**
 * Created by liyang on 2018/7/23.
 */
public class UserVo implements Serializable {
    static final long serialVersionUID = 4143476983938492L;
    private String userName;
    private String password;

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
        return "UserVo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
