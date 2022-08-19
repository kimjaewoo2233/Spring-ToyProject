package com.example.demo.repository;

import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private AccountRepository accountRepository;



    @Test
    void 연관관계테스트(){
        //given
        BoardResponseDto responseDto = new BoardResponseDto(boardRepository.findById(1L).get());

        //when
        responseDto.setAccount(new Account());
        boardRepository.save(responseDto.toEntity());

        //then
        System.out.println(boardRepository.findById(1L).get().getAccount());
        System.out.println(boardRepository.findById(1L));
    }
    @Test
    void joinTest(){
        //given
        Board board = boardRepository.findFetchId(1L).get();
        Board saveBoard = boardRepository.findById(1L).get();

        //when
        //then
        assertEquals(board,saveBoard);
    }
}