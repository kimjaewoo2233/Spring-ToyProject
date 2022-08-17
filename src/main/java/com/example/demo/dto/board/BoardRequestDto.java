package com.example.demo.dto.board;

import com.example.demo.entity.Account;
import com.example.demo.entity.Board;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
    private Account account;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .account(account)
                .build();
    }


}
