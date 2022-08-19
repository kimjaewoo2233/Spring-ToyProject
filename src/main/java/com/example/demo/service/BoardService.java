package com.example.demo.service;

import com.example.demo.dto.board.BoardRequestDto;
import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    public final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto boardSaveDto){
        return boardRepository.save(boardSaveDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(Pageable pageable) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        Page<Board> list = boardRepository.findAll(pageable);

        resultMap.put("list", list.stream().map(BoardResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }

    public BoardResponseDto findById(Long id){
        return new BoardResponseDto(boardRepository.findById(id).get());
    }

    public int updateBoard(BoardRequestDto boardRequestDto){
        return boardRepository.updateBoard(boardRequestDto);
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    public void deleteAll(Long[] deleteId){
        boardRepository.deleteBoard(deleteId);
    }
    @Transactional(readOnly = true)
    public Page<BoardResponseDto> searchBoard(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardResponseDto::from);
    }
}
