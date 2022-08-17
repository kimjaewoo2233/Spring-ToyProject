package com.example.demo.service;

import com.example.demo.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {


    @Autowired
    BoardRepository repository;

    @Autowired
    BoardService service;
    @Test
    void test(){
        repository.findAll().forEach(System.out::println);

        service.findAll(0,5).entrySet().forEach(
                map -> System.out.println(map.getKey()+":"+map.getValue())
        );
    }
}