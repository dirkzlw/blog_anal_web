package com.blog.anal.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ranger
 * @create 2020-01-08 15:46
 */
@Getter
@Setter
@ToString
public class User {
    private String sex;
    private String age;

    public User(int sex, int age) {
        switch (sex){
            case 0:
                this.sex = "female";
                break;
            case 1:
                this.sex = "male";
                break;
            default:
                break;
        }
        switch (age){
            case 0:
                this.age = "10s";
                break;
            case 1:
                this.age = "20s";
                break;
            case 2:
                this.age = "30s";
                break;
            default:
                break;
        }
    }
}
