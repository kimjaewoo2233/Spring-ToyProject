package com.example.demo.entity;


import com.example.demo.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int readCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @CreatedBy
    private String register;

    @Builder
    public Board(Long id, String title, String content, int readCnt, Account account){
        this.id=id;
        this.title=title;
        this.content=content;
        this.readCnt=readCnt;
        this.account=account;
    }

}
