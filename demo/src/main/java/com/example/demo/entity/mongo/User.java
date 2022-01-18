package com.example.demo.entity.mongo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@ToString
@Accessors(chain = true)
public class User {
    @MongoId
    private String id;
    private String name;
    private Gender gender;
    private Integer age;
    private AccountInfo accountInfo;

    public User(String id, String name, Gender gender, Integer age, AccountInfo accountInfo) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.accountInfo = accountInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
