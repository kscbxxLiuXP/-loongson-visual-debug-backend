package com.loongson.debug;

import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.service.ILtlogInstructionMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@SpringBootTest
public class LtlogInstructionMapTest {
    @Autowired
    ILtlogInstructionMapService ltlogInstructionMapService;

    @Test
    void test() {
        ArrayList<LtlogInstructionMap> updateLtlogInstructionList = new ArrayList<>();
//
//        LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
//        ltlogInstructionMap.setLtid(15);
//        ltlogInstructionMap.setIndexx(27394);
//        ltlogInstructionMap.setNum(2300L);
//        LtlogInstructionMap ltlogInstructionMap2 = new LtlogInstructionMap();
//        ltlogInstructionMap2.setLtid(15);
//        ltlogInstructionMap2.setIndexx(28270);
//        ltlogInstructionMap2.setNum(15165L);
//        updateLtlogInstructionList.add(ltlogInstructionMap);
//        updateLtlogInstructionList.add(ltlogInstructionMap2);
        for (int i = 0; i < 5000; i++) {
            LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
            ltlogInstructionMap.setLtid(15);
            ltlogInstructionMap.setIndexx(27394);
            ltlogInstructionMap.setNum(2300L);
            updateLtlogInstructionList.add(ltlogInstructionMap);
        }

        ltlogInstructionMapService.updateBatchById(updateLtlogInstructionList);
        ltlogInstructionMapService.updateBatch(updateLtlogInstructionList);

    }
    @Test
    void jdbcTest() throws SQLException {
        long begin = System.currentTimeMillis();
        ArrayList<LtlogInstructionMap> updateLtlogInstructionList = new ArrayList<>();
//
//        LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
//        ltlogInstructionMap.setLtid(15);
//        ltlogInstructionMap.setIndexx(27394);
//        ltlogInstructionMap.setNum(2300L);
//        LtlogInstructionMap ltlogInstructionMap2 = new LtlogInstructionMap();
//        ltlogInstructionMap2.setLtid(15);
//        ltlogInstructionMap2.setIndexx(28270);
//        ltlogInstructionMap2.setNum(15165L);
//        updateLtlogInstructionList.add(ltlogInstructionMap);
//        updateLtlogInstructionList.add(ltlogInstructionMap2);
        for (int i = 0; i < 5000; i++) {
            LtlogInstructionMap ltlogInstructionMap = new LtlogInstructionMap();
            ltlogInstructionMap.setLtid(15);
            ltlogInstructionMap.setIndexx(27394);
            ltlogInstructionMap.setNum(2300L);
            updateLtlogInstructionList.add(ltlogInstructionMap);
        }


        String url = "jdbc:mysql://liuxinpeng.mysql.rds.aliyuncs.com:3306/loongson_debug?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        String username = "liuxp";
        String password = "LiuXinPeng35";
        //插入sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("update ltlog_instruction_map set num = ? where ltid = ? and indexx = ?");
        int count = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            conn.setAutoCommit(false);
            for (int i = 0; i < updateLtlogInstructionList.size(); i++) {
                System.out.println(i);
                LtlogInstructionMap ltlogInstructionMap = updateLtlogInstructionList.get(i);
                ps.setLong(1, ltlogInstructionMap.getNum());
                ps.setInt(2, ltlogInstructionMap.getLtid());
                ps.setInt(3, ltlogInstructionMap.getIndexx());
                ps.addBatch();
                count++;
                if ((i + 1) % 1000 == 0) {
                    System.out.println("addddd");
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("总计单条循环更新" + count + "条，共计耗时" + (end - begin) / 1000 + "秒");
    }
}
