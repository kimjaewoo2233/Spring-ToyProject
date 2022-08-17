package com.example.demo.dto.board;

import com.example.demo.entity.Account;
import com.example.demo.entity.BaseTimeEntity;
import com.example.demo.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private int readCnt;
    private Account account;

    private String register;

    private LocalDateTime createdAt;



    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.readCnt = entity.getReadCnt();
        this.account = entity.getAccount();
        this.createdAt = entity.getCreatedAt();
        this.register = entity.getRegister();
    }

    public Board toEntity(){
        return new Board(
                id,title,content,readCnt,account
        );
    }

}




