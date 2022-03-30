package com.loongson.debug;

import com.loongson.debug.dto.SearchResultDTO;
import com.loongson.debug.service.SearchService;
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
        List<SearchResultDTO> searchResultDTOS = searchService.searchAddress(27, "0xffe8000", true);
        System.out.println(searchResultDTOS);
    }
}
