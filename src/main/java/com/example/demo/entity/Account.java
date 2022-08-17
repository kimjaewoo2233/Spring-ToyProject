package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Table(indexes = {
        @Index(columnList = "email",unique = true),
        @Index(columnList = "createdAt")
})
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String email;

    private String nickname;


    public static Account of(String userId,String password,String email,String nickname){
        return new Account(userId,password,email,nickname);
    }

    public Account(String userId,String password,String email,String nickname){
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }



}