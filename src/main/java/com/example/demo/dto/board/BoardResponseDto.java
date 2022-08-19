package com.example.demo.dto.board;

import com.example.demo.entity.Account;
import com.example.demo.entity.BaseTimeEntity;
import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
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
    public static BoardResponseDto from(Board entity){
            return new BoardResponseDto(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getReadCnt(),
                    entity.getAccount(),
                    entity.getRegister(),
                    entity.getCreatedAt()
            );
    }

    public Board toEntity(){
        return new Board(
                id,title,content,readCnt,account
        );
    }

}




