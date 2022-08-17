package com.example.demo.controller;

import com.example.demo.dto.board.BoardRequestDto;
import com.example.demo.dto.security.AccountPrincipal;
import com.example.demo.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController{
    private final BoardService boardService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/board/list")
    public String getBoardListpage(Model model,
                                   @RequestParam(required = false,defaultValue = "0")Integer page,
                                   @RequestParam(required = false,defaultValue = "5")Integer size) throws Exception{
        try {
            model.addAttribute("resultMap",boardService.findAll(page,size));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return "/board/list";
    }
    @GetMapping("/board/write")
    public String getBoardWritePage(Model model,
                                    @AuthenticationPrincipal AccountPrincipal principal) {
        model.addAttribute("username",principal.getUsername());
        return "/board/write";
    }

    @GetMapping("/board/view")
    public String getBoardViewPage(Model model,
                                    BoardRequestDto requestDto  ) throws Exception {
        //BoardRequest에 Id 값만 담겨서 넘어온다. 그 값이 null인지만 검사하고 그 값을 통해 DB서 값을 꺼낸다.
        try {
            if (requestDto.getId() != null) {
                model.addAttribute("info", boardService.findById(requestDto.getId()));
            }       //ID값으로 꺼낸 ResponseDTO를 넘겨준다.
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "/board/view";
    }
    @PostMapping("/board/write/action")
    public String boardWriteAction(Model model, BoardRequestDto requestDto) throws Exception {

        log.info("========================",requestDto);
        try {
            Long result = boardService.save(requestDto);

            if (result < 0) {
                throw new Exception("#Exception boardWriteAction!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:/board/list";
    }
}