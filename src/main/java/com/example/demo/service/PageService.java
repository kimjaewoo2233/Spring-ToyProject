package com.example.demo.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class PageService {
    private static final int PAGE_LENGTH = 5;

    public List<Integer> getPagination(int currentPageNumber,int totalPages){
        int startNumber = Math.max(currentPageNumber - (PAGE_LENGTH/2),0);
        int endNumber = Math.min(startNumber +PAGE_LENGTH,totalPages);

        return IntStream.range(startNumber,endNumber).boxed().toList();
    }
}
