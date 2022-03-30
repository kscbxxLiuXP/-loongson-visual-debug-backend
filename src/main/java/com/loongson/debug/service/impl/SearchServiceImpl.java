package com.loongson.debug.service.impl;

import com.loongson.debug.dto.SearchResultDTO;
import com.loongson.debug.dto.TBBlockDTO;
import com.loongson.debug.entity.Head;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.IR2;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.service.IHeadService;
import com.loongson.debug.service.ITbBlockService;
import com.loongson.debug.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    IHeadService headService;
    @Autowired
    ITbBlockService tbBlockService;

    @Override
    public List<SearchResultDTO> searchAddress(int ltid, String address, boolean skipHead) {

        /*
         * 首先将所有的TBBlockDTO加载进来
         * 然后遍历DTO
         *
         * 判断地址是否是完全地址
         * 完全地址：
         *      检查起始地址，判断是否在当前块，
         *      如果在当前块搜索完毕之后可以直接结束搜索
         *
         * 不完全地址：
         *      循环所有的块并且
         *      查找里面是否存在检索关键字
         *
         * 记录位置
         */

        List<SearchResultDTO> searchResults = new ArrayList<>();
        if (!skipHead) {
            Head head = headService.getHeadById(ltid);
        }

        //0xffe8000574
        //是否是全地址模式
        //ir2全地址
        boolean a = address.startsWith("0x8");
        boolean b = address.length() == 9;
        boolean c = address.length() == 12;
        boolean d = address.startsWith("0xff");
        boolean fullAddress = (a && b) || (c && d);
        List<TbBlock> tbBlocks = tbBlockService.getTbBlocks(ltid);
        int snum = 1;
        TBBlockDTO tbBlockDTO = null;
        for (TbBlock tbBlock : tbBlocks) {
            tbBlockDTO = new TBBlockDTO(tbBlock);
            //遍历IR1
            //遍历地址
            String ad1 = tbBlockDTO.getStartAddressIR1();
            String ad2 = tbBlockDTO.getEndAddressIR1();
            String ad3 = tbBlockDTO.getStartAddressIR2();
            String ad4 = tbBlockDTO.getEndAddressIR2();
            if (fullAddress) {
                if (tbBlockDTO.getIR1Instr().size() == 1 && !tbBlockDTO.getIR1Instr().get(0).getAddress().contains(address))
                    continue;
                if (address.length() == 9) {
                    for (IR1 ir1 : tbBlockDTO.getIR1Instr()) {
                        boolean contains = ir1.getAddress().contains(address);
                        boolean contains2 = ir1.getInstruction().getOperand().contains(address);
                        //找到了
                        if (contains || contains2) {
                            //添加记录
                            String selectID = "TB-" + tbBlock.getTbindex() + "-ir1-" + ir1.getId();
                            searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 1, ir1.getId(), selectID));
                            snum++;
                        }
                    }
                } else if (address.length() == 12 && (addressCompare(address, ad3) >= 0 && addressCompare(address, ad4) <= 0)) {
                    //address在当前块的IR2中
                    for (IR2 ir2 : tbBlockDTO.getIR2Instr()) {
                        boolean contains = ir2.getAddress().contains(address);
                        //找到了
                        if (contains) {
                            String selectID = "TB-" + tbBlock.getTbindex() + "-ir2-" + ir2.getId();
                            searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 2, ir2.getId(), selectID));
                            snum++;
                            //添加记录
                            break;
                        }
                    }
                    //结束寻找
                    break;
                }
            } else {

                //不是全地址直接暴力全搜索
                for (IR1 ir1 : tbBlockDTO.getIR1Instr()) {
                    boolean contains = ir1.getAddress().contains(address);
                    boolean contains2 = ir1.getInstruction().getOperand().contains(address);
                    //找到了
                    if (contains || contains2) {
                        //添加记录
                        String selectID = "TB-" + tbBlock.getTbindex() + "-ir1-" + ir1.getId();
                        searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 1, ir1.getId(), selectID));
                        snum++;
                    }
                }
                for (IR2 ir2 : tbBlockDTO.getIR2Instr()) {
                    boolean contains = ir2.getAddress().contains(address);
                    boolean contains2 = ir2.getInstruction().getOperand().contains(address);
                    //找到了
                    if (contains || contains2) {
                        String selectID = "TB-" + tbBlock.getTbindex() + "-ir2-" + ir2.getId();
                        searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 2, ir2.getId(), selectID));
                        snum++;
                        //添加记录
                    }
                }
            }
        }
        return searchResults;
    }

    @Override
    public List<SearchResultDTO> searchInstruction(int type, int ltid, String instruction, boolean skipHead) {
        List<SearchResultDTO> searchResults = new ArrayList<>();
        if (!skipHead) {
            Head head = headService.getHeadById(ltid);
        }
        List<TbBlock> tbBlocks = tbBlockService.getTbBlocks(ltid);
        int snum = 1;
        TBBlockDTO tbBlockDTO = null;
        for (TbBlock tbBlock : tbBlocks) {
            tbBlockDTO = new TBBlockDTO(tbBlock);
            if (type == 1 || type == 2) {
                for (IR1 ir1 : tbBlockDTO.getIR1Instr()) {
                    boolean contains = ir1.getInstruction().getOperator().contains(instruction);

                    //找到了
                    if (contains) {
                        //添加记录
                        String selectID = "TB-" + tbBlock.getTbindex() + "-ir1-" + ir1.getId();
                        searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 1, ir1.getId(), selectID));
                        snum++;
                    }
                }
            }

            if (type == 1 || type == 3) {
                for (IR2 ir2 : tbBlockDTO.getIR2Instr()) {
                    boolean contains = ir2.getInstruction().getOperator().contains(instruction);

                    //找到了
                    if (contains) {
                        String selectID = "TB-" + tbBlock.getTbindex() + "-ir2-" + ir2.getId();
                        searchResults.add(new SearchResultDTO(snum, tbBlock.getTbindex(), 2, ir2.getId(), selectID));
                        snum++;
                        //添加记录
                    }
                }
            }

        }
        return searchResults;
    }

    public int addressCompare(String address1, String address2) {
        long l = Long.parseLong(address1.substring(2), 16);
        long l2 = Long.parseLong(address2.substring(2), 16);

        if (l == l2)
            return 0;
        if (l > l2)
            return 1;
        else
            return -1;
    }
}
