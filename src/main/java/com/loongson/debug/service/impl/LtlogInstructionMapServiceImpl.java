package com.loongson.debug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongson.debug.dto.LtlogInstructionMapDTO;
import com.loongson.debug.dto.PatternDTO;
import com.loongson.debug.dto.PatternFilter;
import com.loongson.debug.dto.QueryInstructionAllDTO;
import com.loongson.debug.entity.LtlogInstructionMap;
import com.loongson.debug.entity.LtlogInstructionPattern;
import com.loongson.debug.mapper.LtlogInstructionMapMapper;
import com.loongson.debug.mapper.LtlogInstructionPatternMapper;
import com.loongson.debug.service.ILtlogInstructionMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loongson.debug.service.ILtlogInstructionPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuxp
 * @since 2022-04-18
 */
@Service
public class LtlogInstructionMapServiceImpl extends ServiceImpl<LtlogInstructionMapMapper, LtlogInstructionMap> implements ILtlogInstructionMapService {
    @Autowired
    LtlogInstructionMapMapper ltlogInstructionMapMapper;

    @Autowired
    LtlogInstructionPatternMapper ltlogInstructionPatternMapper;

    @Override
    public HashMap<String, Object> selectByPage(String operator, String orderby, String order, int ltid, int currentPage, int limit) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        QueryWrapper<LtlogInstructionMap> wrapper2 = new QueryWrapper<>();
        wrapper.select("*, num * ir2num as ir2execute");
        wrapper2.eq("ltid", ltid);
        wrapper.eq("ltid", ltid);
        if (order.equals("asc")) {
            wrapper.orderByAsc(orderby);
        } else {
            wrapper.orderByDesc(orderby);
        }
        if (operator != "") {
            wrapper.eq("operator", operator);
            wrapper2.eq("operator", operator);
        }
        Page<LtlogInstructionMap> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionMap> IPage = ltlogInstructionMapMapper.selectPage(page, wrapper);


        wrapper2.select("sum(num) as sumir1, sum(num*ir2num) as sumir2");
        LtlogInstructionMap ltlogInstructionMap = ltlogInstructionMapMapper.selectOne(wrapper2);

        List<LtlogInstructionMap> ltlogInstructionMapList = IPage.getRecords();
        long pages = IPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", ltlogInstructionMapList);
        returnRes.put("total", IPage.getTotal());
        returnRes.put("sumir1", ltlogInstructionMap.getSumir1());
        returnRes.put("sumir2", ltlogInstructionMap.getSumir2());
        returnRes.put("pages", pages);
        return returnRes;
    }

    @Override
    public List<LtlogInstructionPattern> getLtlogInstructionMapsComboed(int ltid) {
        return ltlogInstructionMapMapper.getLtlogInstructionMapsComboed(ltid);
    }

    @Override
    public List<LtlogInstructionMap> getChartData(int ltid) {
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.select("operator", "operand", "num").eq("ltid", ltid).orderByDesc("num");
        return ltlogInstructionMapMapper.selectList(wrapper);
    }

    @Override
    public List<String> getInstructionTypes(int ltid) {
        ArrayList<String> instructionTypes = new ArrayList<>();
        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.select("operator").groupBy("operator");
        List<LtlogInstructionMap> ltlogInstructionMapList = ltlogInstructionMapMapper.selectList(wrapper);
        for (LtlogInstructionMap ltlogInstructionMap : ltlogInstructionMapList) {
            instructionTypes.add(ltlogInstructionMap.getOperator());
        }

        return instructionTypes;
    }

    @Override
    public HashMap<String, Object> getLtlogInstructionMapsAll(QueryInstructionAllDTO queryInstructionAllDTO) {
        String operator = queryInstructionAllDTO.getOperator();
        String orderby = queryInstructionAllDTO.getOrderby();
        String order = queryInstructionAllDTO.getOrder();
        int ltid = queryInstructionAllDTO.getLtid();
        int currentPage = queryInstructionAllDTO.getCurrentPage();
        int limit = queryInstructionAllDTO.getLimit();

        int limitlow = (currentPage - 1) * limit;
        if (limitlow != 0) {
            limitlow = limitlow - 1;
            //因为要在获取分页后，插入组合数据，所以需要知道当前分页的上限和下限
        }

        //剔除不显示的元素
        List<PatternFilter> comboed = queryInstructionAllDTO.getComboed();
        List<PatternFilter> hidden = queryInstructionAllDTO.getHidden();
        List<String> hiddenOperator = queryInstructionAllDTO.getHiddenOperator();
        List<String> comboedString = new ArrayList<>();
        List<String> hiddenString = new ArrayList<>();
        Set<String> set = new HashSet<>();
        comboed.forEach(e -> {
            String s = e.getOperator() + e.getPattern();
            set.add(s);
            comboedString.add(s);
        });
        hidden.forEach(e -> {
            String s = e.getOperator() + e.getPattern();
            set.add(s);
            hiddenString.add(s);
        });
        List<String> notInList = new ArrayList<>(set);

        //第0页获取0-limit+1个，其他的获取 当前页(first-1项)~(last+1项)一共(limit+2项)
        List<Object> list = ltlogInstructionMapMapper.getLtlogInstructionMapsAll(operator, orderby, order, ltid, limitlow, limitlow == 0 ? limit + 1 : limit + 2, notInList, hiddenOperator);
        List<LtlogInstructionMap> ltlogInstructionMapsAll = (List<LtlogInstructionMap>) list.get(0);
        Integer total = ((List<Integer>) list.get(1)).get(0);//总量
        String lastkey = "";
        String startKey = "";
        //处理分组信息,
        //处理下列异常：
        //jne 只有一种pattern模式
        //如果筛选jne又将它合并则会出现从数据库抓取的数据为空

        startKey = ltlogInstructionMapsAll.size() == 0 ? "" : ltlogInstructionMapsAll.get(0).getUid();
        lastkey = ltlogInstructionMapsAll.size() == 0 ? "" : ltlogInstructionMapsAll.get(ltlogInstructionMapsAll.size() - 1).getUid();

        if (comboedString.size() != 0) {
            //获取分组
            List<LtlogInstructionPattern> ltlogInstructionPatterns = ltlogInstructionPatternMapper.selectList(new QueryWrapper<LtlogInstructionPattern>().eq("ltid", ltid).in("concat(operator,pattern)", comboedString));
            ltlogInstructionPatterns.forEach(e -> {
                ltlogInstructionMapsAll.add(new LtlogInstructionMap(e));
            });
            ltlogInstructionMapsAll.sort(getComparator(orderby, order));
        }

        //从startuid开始截取limit个元素
        boolean find = limitlow == 0;

        ArrayList<LtlogInstructionMap> result = new ArrayList<>();
        for (LtlogInstructionMap ltlogInstructionMap : ltlogInstructionMapsAll) {
            String key = ltlogInstructionMap.getUid() != null ? ltlogInstructionMap.getUid() : "";
            if (key.equals(startKey) && limitlow != 0) {
                //非第一页
                //找到上限之后开始记录
                find = true;
                continue;
            }
            if (key.equals(lastkey)) {
                //找到下限
                find = false;
                //如果是尾页，最后一个元素也要被添加
                if (currentPage == (total / limit + 1)) {
                    String p = ltlogInstructionMap.getOperator() + ltlogInstructionMap.getPattern();
                    if (!hiddenString.contains(p)) {
                        result.add(ltlogInstructionMap);
                    }
                }
            }
            if (find) {
                String p = ltlogInstructionMap.getOperator() + ltlogInstructionMap.getPattern();
                if (!hiddenString.contains(p) && !hiddenOperator.contains(ltlogInstructionMap.getOperator())) {
                    result.add(ltlogInstructionMap);
                }

            }

        }


        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", result);
        returnRes.put("total", total);
        returnRes.put("sumir1", ltlogInstructionMapsAll.get(0).getSumir1());
        returnRes.put("sumir2", ltlogInstructionMapsAll.get(0).getSumir2());
        returnRes.put("pages", total / limit);
        System.out.println(returnRes);
        return returnRes;
    }

    public Comparator<LtlogInstructionMap> getComparator(String orderby, String order) {
        Comparator<LtlogInstructionMap> comparator = new Comparator<LtlogInstructionMap>() {
            @Override
            public int compare(LtlogInstructionMap o1, LtlogInstructionMap o2) {
                switch (orderby) {
                    case "ir2num":
                        if (o1.getIr2num() > o2.getIr2num()) {
                            return order.equals("desc") ? -1 : 1;
                        } else if (o1.getIr2num() == o2.getIr2num()) {
                            return 0;
                        } else {
                            return order.equals("desc") ? 1 : -1;
                        }
                    case "ir1execute":
                        if (o1.getIr1execute() > o2.getIr1execute()) {
                            return order.equals("desc") ? -1 : 1;
                        } else if (Objects.equals(o1.getIr1execute(), o2.getIr1execute())) {
                            return 0;
                        } else {
                            return order.equals("desc") ? 1 : -1;
                        }
                    case "ir2execute":
                        if (o1.getIr2execute() > o2.getIr2execute()) {
                            return order.equals("desc") ? -1 : 1;
                        } else if (Objects.equals(o1.getIr2execute(), o2.getIr2execute())) {
                            return 0;
                        } else {
                            return order.equals("desc") ? 1 : -1;
                        }
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public HashMap<String, Object> getLtlogInstructionMapsSpecific(QueryInstructionAllDTO queryInstructionAllDTO) {
        String operator = queryInstructionAllDTO.getOperator();
        String pattern = queryInstructionAllDTO.getPattern();

        String orderby = queryInstructionAllDTO.getOrderby();
        String order = queryInstructionAllDTO.getOrder();
        int ltid = queryInstructionAllDTO.getLtid();
        int currentPage = queryInstructionAllDTO.getCurrentPage();
        int limit = queryInstructionAllDTO.getLimit();

        QueryWrapper<LtlogInstructionMap> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", ltid);
        if (order.equals("asc")) {
            wrapper.orderByAsc(orderby);
        } else {
            wrapper.orderByDesc(orderby);
        }
        if (operator != "") {
            wrapper.eq("operator", operator);
        }
        if (pattern != "") {
            wrapper.eq("pattern", pattern);
        }

        Page<LtlogInstructionMap> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionMap> IPage = ltlogInstructionMapMapper.selectPage(page, wrapper);

        List<LtlogInstructionMap> ltlogInstructionMaps = IPage.getRecords();
        long pages = IPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", ltlogInstructionMaps);
        returnRes.put("total", IPage.getTotal());
        returnRes.put("sumir1", ltlogInstructionMaps.get(0).getSumir1());
        returnRes.put("sumir2", ltlogInstructionMaps.get(0).getSumir2());
        returnRes.put("pages", pages);

        return returnRes;
    }

    @Override
    public HashMap<String, Object> getLtlogInstructionMapsAllPatterned(QueryInstructionAllDTO queryInstructionAllDTO) {
        String operator = queryInstructionAllDTO.getOperator();
        String orderby = queryInstructionAllDTO.getOrderby();
        String order = queryInstructionAllDTO.getOrder();
        int ltid = queryInstructionAllDTO.getLtid();
        int currentPage = queryInstructionAllDTO.getCurrentPage();
        int limit = queryInstructionAllDTO.getLimit();
        List<PatternFilter> hidden = queryInstructionAllDTO.getHidden();
        List<String> hiddenString = new ArrayList<>();
        List<String> hiddenOperator = queryInstructionAllDTO.getHiddenOperator();

        hidden.forEach(e -> {
            String s = e.getOperator() + e.getPattern();
            hiddenString.add(s);
        });
        QueryWrapper<LtlogInstructionPattern> wrapper = new QueryWrapper<>();
        wrapper.eq("ltid", ltid);
        if (order.equals("asc")) {
            wrapper.orderByAsc(orderby);
        } else {
            wrapper.orderByDesc(orderby);
        }
        if (operator != "") {
            wrapper.eq("operator", operator);
        }
        wrapper.notIn(hiddenString.size() != 0, "concat(operator,pattern)", hiddenString);
        wrapper.notIn(hiddenOperator.size() != 0, "operator", hiddenOperator);
        Page<LtlogInstructionPattern> page = new Page<>(currentPage, limit);
        IPage<LtlogInstructionPattern> IPage = ltlogInstructionPatternMapper.selectPage(page, wrapper);

        List<LtlogInstructionPattern> ltlogInstructionPatterns = IPage.getRecords();
        long pages = IPage.getPages();

        HashMap<String, Object> returnRes = new HashMap<>();
        returnRes.put("records", ltlogInstructionPatterns);
        returnRes.put("total", IPage.getTotal());
        returnRes.put("sumir1", ltlogInstructionPatterns.get(0).getSumir1all());
        returnRes.put("sumir2", ltlogInstructionPatterns.get(0).getSumir2all());
        returnRes.put("pages", pages);

        return returnRes;
    }

}

