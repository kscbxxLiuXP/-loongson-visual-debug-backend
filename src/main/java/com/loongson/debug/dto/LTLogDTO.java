package com.loongson.debug.dto;

import java.util.ArrayList;

public class LTLogDTO {
    private int id;
    private String head;//头部
    private ArrayList<TBBlockDTO> tbBlockDtos;//TB块列表
    private ArrayList<String> addresses;//预存所有的地址
}

