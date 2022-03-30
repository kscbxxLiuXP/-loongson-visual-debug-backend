package com.loongson.debug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loongson.debug.dto.TBBlockDTO;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.ITbBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class TbBlockController {

    @Autowired
    ITbBlockService tbBlockService;

    @GetMapping("/tbblocks")
    public HashMap<String, Object> getTbblocks(int id, int currentPage, int limit) {
        IPage<TbBlock> tbBlockIPage = tbBlockService.selectByPage(id, currentPage, limit);
        List<TbBlock> tbBlocks = tbBlockIPage.getRecords();
        long pages = tbBlockIPage.getPages();

        List<TBBlockDTO> tbBlockDTOList = new ArrayList<>();
        for (TbBlock tbBlock : tbBlocks) {
            TBBlockDTO tbBlockDTO = new TBBlockDTO(tbBlock);
            tbBlockDTOList.add(tbBlockDTO);

        }

        HashMap<String, Object> returnRes = new HashMap<>();

        returnRes.put("records", tbBlockDTOList);
        returnRes.put("total", tbBlockIPage.getTotal());
        returnRes.put("pages", pages);
        return returnRes;
    }
}
