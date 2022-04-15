package com.loongson.debug;

import com.loongson.debug.dto.SearchResultDTO;
import com.loongson.debug.service.SearchService;
import com.loongson.debug.util.RunTimer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SearchTest {

    @Autowired
    SearchService searchService;

    @Test
    void searchAddress(){
        List<SearchResultDTO> searchResultDTOS = searchService.searchAddress(8, "0xffe8000", true);
        System.out.println(searchResultDTOS);
    }

    @Test
    void searchTimeTest(){
        RunTimer runTimer = new RunTimer(true);
        runTimer.start();
        searchService.searchAddress2(8,"0x42");
        runTimer.end("search");
    }

}
