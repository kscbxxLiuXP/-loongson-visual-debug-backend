package com.loongson.debug;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.mapper.TbBlockMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageTest {

    @Autowired
    TbBlockMapper tbBlockMapper;

    @Test
    void pageTest() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ltid", 26);
        Page<TbBlock> tbBlockPage = new Page<>(1, 20);
        IPage<TbBlock> tbBlockIPage = tbBlockMapper.selectPage(tbBlockPage, queryWrapper);
        System.out.println("总页数： " + tbBlockIPage.getPages());
        System.out.println("总记录数： " + tbBlockIPage.getTotal());
        int size = tbBlockIPage.getRecords().size();
        System.out.println(size);
    }
}
