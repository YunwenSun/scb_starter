package com.example.demo.entity.mongo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class AccountInfo {

    private String username;
    private String password;
    private String mobileNumber;

    public AccountInfo(String username, String password, String mobileNumber) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}
