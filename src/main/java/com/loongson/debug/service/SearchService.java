package com.loongson.debug.service;

import com.loongson.debug.dto.SearchResultDTO;

import java.util.List;

public interface SearchService {

    List<SearchResultDTO> searchAddress(int ltid, String address, boolean skipHead);

    List<SearchResultDTO> searchInstruction(int type, int ltid, String instruction, boolean skipHead);
}
