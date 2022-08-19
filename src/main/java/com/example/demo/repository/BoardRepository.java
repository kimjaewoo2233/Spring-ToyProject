package com.example.demo.repository;


import com.example.demo.dto.board.BoardRequestDto;
import com.example.demo.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long> {
    static final String UPDATE_BOARD = "UPDATE Board "
            + "SET TITLE = :#{#boardRequestDto.title}, "
            + "CONTENT = :#{#boardRequestDto.content}, "
            + "UPDATE_TIME = NOW() "
            + "WHERE ID = :#{#boardRequestDto.id}";

    static final String UPDATE_BOARD_READ_CNT_INC = "UPDATE Board "
            + "SET READ_CNT = READ_CNT + 1 "
            + "WHERE ID = :id";

    static final String DELETE_BOARD = "DELETE FROM Board "
            + "WHERE ID IN (:deleteList)";

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD, nativeQuery = true)
    public int updateBoard(@Param("boardRequestDto") BoardRequestDto boardRequestDto);

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD_READ_CNT_INC, nativeQuery = true)
    public int updateBoardReadCntInc(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = DELETE_BOARD, nativeQuery = true)
    public int deleteBoard(@Param("deleteList") Long[] deleteList);


    @Query(value = "select b from Board b join fetch b.account where b.id = :id")
    public Optional<Board> findFetchId(Long id);

}
