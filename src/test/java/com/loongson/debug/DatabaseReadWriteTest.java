package com.loongson.debug;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.dto.JsonTestDTO;
import com.loongson.debug.entity.JsonTest;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.IJsonTestService;
import com.loongson.debug.service.ITbBlockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DatabaseReadWriteTest {
    @Autowired
    IJsonTestService jsonTestService;

    @Autowired
    ITbBlockService tbBlockService;

    @Test
    void databaseJsonWriteTest() {
        JsonTest jsonTest = jsonTestService.getById(1);
        JsonTestDTO jsonTestDTO = new JsonTestDTO(jsonTest);
        System.out.println(jsonTest);

    }

    private static String s = "{\"ltid\":1,\"endaddressir1\":\"0x804999b\",\"endaddressir2\":\"0xffe80005c4\",\"tbindex\":0,\"ir1instr\":\"[{\\\"address\\\":\\\"0x8049990\\\",\\\"id\\\":0,\\\"instruction\\\":{\\\"operand\\\":\\\"ebp, ebp\\\",\\\"operator\\\":\\\"xor\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x8049992\\\",\\\"id\\\":1,\\\"instruction\\\":{\\\"operand\\\":\\\"esi\\\",\\\"operator\\\":\\\"pop\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x8049993\\\",\\\"id\\\":2,\\\"instruction\\\":{\\\"operand\\\":\\\"ecx, esp\\\",\\\"operator\\\":\\\"mov\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x8049995\\\",\\\"id\\\":3,\\\"instruction\\\":{\\\"operand\\\":\\\"esp, 0xfffffff0\\\",\\\"operator\\\":\\\"and\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x8049998\\\",\\\"id\\\":4,\\\"instruction\\\":{\\\"operand\\\":\\\"eax\\\",\\\"operator\\\":\\\"push\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x8049999\\\",\\\"id\\\":5,\\\"instruction\\\":{\\\"operand\\\":\\\"esp\\\",\\\"operator\\\":\\\"push\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x804999a\\\",\\\"id\\\":6,\\\"instruction\\\":{\\\"operand\\\":\\\"edx\\\",\\\"operator\\\":\\\"push\\\",\\\"type\\\":1}},{\\\"address\\\":\\\"0x804999b\\\",\\\"id\\\":7,\\\"instruction\\\":{\\\"operand\\\":\\\"0x80499c3\\\",\\\"operator\\\":\\\"call\\\",\\\"type\\\":1}}]\",\"ir1num\":8,\"ir2instr\":\"[{\\\"address\\\":\\\"0xffe8000540\\\",\\\"id\\\":\\\"001\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp0,$s5,0\\\",\\\"operator\\\":\\\"slli.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0040838c\\\"},{\\\"address\\\":\\\"0xffe8000544\\\",\\\"id\\\":\\\"002\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1,$s5,0\\\",\\\"operator\\\":\\\"slli.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0040838d\\\"},{\\\"address\\\":\\\"0xffe8000548\\\",\\\"id\\\":\\\"003\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1\\\",\\\"operator\\\":\\\"orn\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0015b58e\\\"},{\\\"address\\\":\\\"0xffe800054c\\\",\\\"id\\\":\\\"004\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s5,itmp2,$zero\\\",\\\"operator\\\":\\\"xor\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x001501dc\\\"},{\\\"address\\\":\\\"0xffe8000550\\\",\\\"id\\\":\\\"006\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s6,$s4,0\\\",\\\"operator\\\":\\\"ld.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x2880037d\\\"},{\\\"address\\\":\\\"0xffe8000554\\\",\\\"id\\\":\\\"007\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,4\\\",\\\"operator\\\":\\\"lu52i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x02c0137b\\\"},{\\\"address\\\":\\\"0xffe8000558\\\",\\\"id\\\":\\\"009\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s1,$s4,31,0\\\",\\\"operator\\\":\\\"slti\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x00df0378\\\"},{\\\"address\\\":\\\"0xffe800055c\\\",\\\"id\\\":\\\"011\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp0,$s4,0\\\",\\\"operator\\\":\\\"slli.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0040836c\\\"},{\\\"address\\\":\\\"0xffe8000560\\\",\\\"id\\\":\\\"012\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1,$zero,16\\\",\\\"operator\\\":\\\"addi.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0280400d\\\"},{\\\"address\\\":\\\"0xffe8000564\\\",\\\"id\\\":\\\"013\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1\\\",\\\"operator\\\":\\\"slt\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0011b40d\\\"},{\\\"address\\\":\\\"0xffe8000568\\\",\\\"id\\\":\\\"014\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1\\\",\\\"operator\\\":\\\"or\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x0014b58e\\\"},{\\\"address\\\":\\\"0xffe800056c\\\",\\\"id\\\":\\\"015\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp1\\\",\\\"operator\\\":\\\"x86and.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x003fb592\\\"},{\\\"address\\\":\\\"0xffe8000570\\\",\\\"id\\\":\\\"016\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,itmp2,$zero\\\",\\\"operator\\\":\\\"xor\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x001501db\\\"},{\\\"address\\\":\\\"0xffe8000574\\\",\\\"id\\\":\\\"018\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,31,0\\\",\\\"operator\\\":\\\"slti\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x00df037b\\\"},{\\\"address\\\":\\\"0xffe8000578\\\",\\\"id\\\":\\\"019\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s0,$s4,-4\\\",\\\"operator\\\":\\\"st.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x29bff377\\\"},{\\\"address\\\":\\\"0xffe800057c\\\",\\\"id\\\":\\\"020\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,-4\\\",\\\"operator\\\":\\\"lu52i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x02fff37b\\\"},{\\\"address\\\":\\\"0xffe8000580\\\",\\\"id\\\":\\\"022\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,-4\\\",\\\"operator\\\":\\\"st.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x29bff37b\\\"},{\\\"address\\\":\\\"0xffe8000584\\\",\\\"id\\\":\\\"023\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,-4\\\",\\\"operator\\\":\\\"lu52i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x02fff37b\\\"},{\\\"address\\\":\\\"0xffe8000588\\\",\\\"id\\\":\\\"025\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s2,$s4,-4\\\",\\\"operator\\\":\\\"st.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x29bff379\\\"},{\\\"address\\\":\\\"0xffe800058c\\\",\\\"id\\\":\\\"026\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,-4\\\",\\\"operator\\\":\\\"lu52i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x02fff37b\\\"},{\\\"address\\\":\\\"0xffe8000590\\\",\\\"id\\\":\\\"028\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s1,$s1,0\\\",\\\"operator\\\":\\\"slli.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x00408318\\\"},{\\\"address\\\":\\\"0xffe8000594\\\",\\\"id\\\":\\\"029\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s5,$s5,31,0\\\",\\\"operator\\\":\\\"slti\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x00df039c\\\"},{\\\"address\\\":\\\"0xffe8000598\\\",\\\"id\\\":\\\"030\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp0,0x8049\\\",\\\"operator\\\":\\\"lu32i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x1410092c\\\"},{\\\"address\\\":\\\"0xffe800059c\\\",\\\"id\\\":\\\"031\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp0,0x9a0\\\",\\\"operator\\\":\\\"xori\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x03a6818c\\\"},{\\\"address\\\":\\\"0xffe80005a0\\\",\\\"id\\\":\\\"032\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp0,$s4,-4\\\",\\\"operator\\\":\\\"st.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x29bff36c\\\"},{\\\"address\\\":\\\"0xffe80005a4\\\",\\\"id\\\":\\\"033\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$s4,$s4,-4\\\",\\\"operator\\\":\\\"lu52i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x02fff37b\\\"},{\\\"address\\\":\\\"0xffe80005a8\\\",\\\"id\\\":\\\"035\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"0\\\",\\\"operator\\\":\\\"bl\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x50000000\\\"},{\\\"address\\\":\\\"0xffe80005ac\\\",\\\"id\\\":\\\"036\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$fp,0xe8000\\\",\\\"operator\\\":\\\"lu32i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x15d00016\\\"},{\\\"address\\\":\\\"0xffe80005b0\\\",\\\"id\\\":\\\"037\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$fp,$fp,0x440\\\",\\\"operator\\\":\\\"xori\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x039102d6\\\"},{\\\"address\\\":\\\"0xffe80005b4\\\",\\\"id\\\":\\\"038\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$fp,255\\\",\\\"operator\\\":\\\"pcaddi\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x16001ff6\\\"},{\\\"address\\\":\\\"0xffe80005b8\\\",\\\"id\\\":\\\"039\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$x,0x8049\\\",\\\"operator\\\":\\\"lu32i.d\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x14100935\\\"},{\\\"address\\\":\\\"0xffe80005bc\\\",\\\"id\\\":\\\"040\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"$x,$x,0x9c3\\\",\\\"operator\\\":\\\"xori\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x03a70eb5\\\"},{\\\"address\\\":\\\"0xffe80005c0\\\",\\\"id\\\":\\\"041\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"itmp10,$fp,0x0\\\",\\\"operator\\\":\\\"xori\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x038002cb\\\"},{\\\"address\\\":\\\"0xffe80005c4\\\",\\\"id\\\":\\\"042\\\",\\\"instruction\\\":{\\\"operand\\\":\\\"-314\\\",\\\"operator\\\":\\\"bl\\\",\\\"type\\\":2},\\\"instructionHex\\\":\\\"0x53fb1bff\\\"}]\",\"ir2map\":\"[]\",\"ir2num\":43,\"startaddressir1\":\"0x8049990\",\"startaddressir2\":\"0xffe8000540\",\"tbaddress\":\"0xffe8000440\"}\n";

    @Test
    void databaseBigDataWriteTest() {

        TbBlock tbBlock = JSON.parseObject(s, TbBlock.class);
        ArrayList<TbBlock> tbBlocks = new ArrayList<>();


        for (int i = 0; i < 30000; i++) {
            tbBlocks.add(tbBlock);
        }

//        long start = System.currentTimeMillis();
//
//        System.out.println(tbBlocks);
//
//        long end = System.currentTimeMillis();
//        System.out.println("用时" + (end - start) + "ms");
        long start = System.currentTimeMillis();


        tbBlockService.saveBatch(tbBlocks);
        long end = System.currentTimeMillis();
        System.out.println("用时" + (end - start) + "ms");

    }

    @Test
    //读取全部tbblocks时速度很慢
    void ReadSimpleTbBlockTest() {
        long startTime = System.currentTimeMillis();
        List<TbBlock> tbBlocks = tbBlockService.getTbBlocksSimple(8);
        System.out.println(tbBlocks.size());
        long endTime = System.currentTimeMillis();
        System.out.println("数据库读取SimpleTB块用时:" + (endTime - startTime) + "ms");
    }
    @Test
    //读取全部tbblocks时速度很慢
    void ReadEntireBlockTest() {
        long startTime = System.currentTimeMillis();
        List<TbBlock> tbBlocks = tbBlockService.getTbBlocks(8);
        System.out.println(tbBlocks.size());
        long endTime = System.currentTimeMillis();
        System.out.println("数据库读取EntireTB块用时:" + (endTime - startTime) + "ms");
    }
}
