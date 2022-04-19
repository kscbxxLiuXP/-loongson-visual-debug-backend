package com.loongson.debug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 统计一次日志的所有执行信息，包括执行效率，各项统计等等
 */
@TableName("lt_log_analysis")
public class LtlogAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Integer ltid;

    //gen_code_size = 4988200/536852312
    @TableField("gen_code_size_1")
    private Long genCodeSize1;
    @TableField("gen_code_size_2")
    private Long genCodeSize2;

    private Integer tbCount;

    //31 max=1189 bytes
    private Integer tbAvgTargetSize;
    private Integer tbAvgTargetSizeMax;

    //TB avg host size    134 bytes (expansion ratio: 4.2)
    private Integer tbAvgHostSize;
    private Double tbAvgHostSizeExpansionRatio;

    //cross page TB count 77 (0%)
    private Integer crossPageTbCount;
    private Double crossPageTbCountPercent;

    //direct jump count   8636 (76%) (2 jumps=7108 62%)
    private Integer directJumpCount;
    private Double directJumpCountPercent;
    @TableField("direct_jump_count_2_jumps")
    private Integer directJumpCount2Jumps;
    @TableField("direct_jump_count_2_jumps_percent")
    private Double directJumpCount2JumpsPercent;

    //tb run times        10953589730
    private Long tbRunTimes;
    //tb exit times       1198496
    private Long tbExitTimes;

    //TB hash buckets     6112/8192 (74.61% head buckets used)
    @TableField("tb_hash_buckets_1")
    private Integer tbHashBuckets1;
    @TableField("tb_hash_buckets_2")
    private Integer tbHashBuckets2;
    private Double tbHashBucketsPercent;

    //TB hash occupancy   33.59%
    private Double tbHashOccupancy;
    //TB hash avg chain   1.018 buckets
    private Double tbHashAvgChain;

    private Boolean profiled;

    public LtlogAnalysis() {
    }

    public LtlogAnalysis(int ltid) {
        this.ltid = ltid;
        this.profiled = false;
    }

    public LtlogAnalysis(Integer ltid, Long genCodeSize1, Long genCodeSize2, Integer tbCount, Integer tbAvgTargetSize, Integer tbAvgTargetSizeMax, Integer tbAvgHostSize, Double tbAvgHostSizeExpansionRatio, Integer crossPageTbCount, Double crossPageTbCountPercent, Integer directJumpCount, Double directJumpCountPercent, Integer directJumpCount2Jumps, Double directJumpCount2JumpsPercent, Long tbRunTimes, Long tbExitTimes, Integer tbHashBuckets1, Integer tbHashBuckets2, Double tbHashBucketsPercent, Double tbHashOccupancy, Double tbHashAvgChain) {
        this.ltid = ltid;
        this.genCodeSize1 = genCodeSize1;
        this.genCodeSize2 = genCodeSize2;
        this.tbCount = tbCount;
        this.tbAvgTargetSize = tbAvgTargetSize;
        this.tbAvgTargetSizeMax = tbAvgTargetSizeMax;
        this.tbAvgHostSize = tbAvgHostSize;
        this.tbAvgHostSizeExpansionRatio = tbAvgHostSizeExpansionRatio;
        this.crossPageTbCount = crossPageTbCount;
        this.crossPageTbCountPercent = crossPageTbCountPercent;
        this.directJumpCount = directJumpCount;
        this.directJumpCountPercent = directJumpCountPercent;
        this.directJumpCount2Jumps = directJumpCount2Jumps;
        this.directJumpCount2JumpsPercent = directJumpCount2JumpsPercent;
        this.tbRunTimes = tbRunTimes;
        this.tbExitTimes = tbExitTimes;
        this.tbHashBuckets1 = tbHashBuckets1;
        this.tbHashBuckets2 = tbHashBuckets2;
        this.tbHashBucketsPercent = tbHashBucketsPercent;
        this.tbHashOccupancy = tbHashOccupancy;
        this.tbHashAvgChain = tbHashAvgChain;
        this.profiled = false;
    }

    @Override
    public String toString() {
        return "Translation buffer state:\n" +
                "gen code size       " + genCodeSize1 + "/" + genCodeSize2 + "\n" +
                "TB count            " + tbCount + "\n" +
                "TB avg target size  " + tbAvgTargetSize + " max=" + tbAvgTargetSizeMax + " bytes\n" +
                "TB avg host size    " + tbAvgHostSize + " bytes (expansion ratio: " + tbAvgHostSizeExpansionRatio + ")\n" +
                "cross page TB count " + crossPageTbCount + " (" + crossPageTbCountPercent + " %)\n" +
                "direct jump count   " + directJumpCount + " (" + directJumpCountPercent + "%) (2 jumps=" + directJumpCount2Jumps + " " + directJumpCount2JumpsPercent + "%)\n" +
                "tb run times        " + tbRunTimes + "\n" +
                "tb exit times       " + tbExitTimes + "\n" +
                "TB hash buckets     " + tbHashBuckets1 + "/" + tbHashBuckets2 + " (" + tbHashBucketsPercent + "% head buckets used)\n" +
                "TB hash occupancy   " + tbHashOccupancy + "% avg chain occ.\n" +
                "TB hash avg chain   " + tbHashAvgChain + " buckets. Histogram: 1|█▁|2\n" +
                "";
    }

    public Boolean getProfiled() {
        return profiled;
    }

    public void setProfiled(Boolean profiled) {
        this.profiled = profiled;
    }


    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public Long getGenCodeSize1() {
        return genCodeSize1;
    }

    public void setGenCodeSize1(Long genCodeSize1) {
        this.genCodeSize1 = genCodeSize1;
    }

    public Long getGenCodeSize2() {
        return genCodeSize2;
    }

    public void setGenCodeSize2(Long genCodeSize2) {
        this.genCodeSize2 = genCodeSize2;
    }

    public Integer getTbCount() {
        return tbCount;
    }

    public void setTbCount(Integer tbCount) {
        this.tbCount = tbCount;
    }

    public Integer getTbAvgTargetSize() {
        return tbAvgTargetSize;
    }

    public void setTbAvgTargetSize(Integer tbAvgTargetSize) {
        this.tbAvgTargetSize = tbAvgTargetSize;
    }

    public Integer getTbAvgTargetSizeMax() {
        return tbAvgTargetSizeMax;
    }

    public void setTbAvgTargetSizeMax(Integer tbAvgTargetSizeMax) {
        this.tbAvgTargetSizeMax = tbAvgTargetSizeMax;
    }

    public Integer getTbAvgHostSize() {
        return tbAvgHostSize;
    }

    public void setTbAvgHostSize(Integer tbAvgHostSize) {
        this.tbAvgHostSize = tbAvgHostSize;
    }

    public Double getTbAvgHostSizeExpansionRatio() {
        return tbAvgHostSizeExpansionRatio;
    }

    public void setTbAvgHostSizeExpansionRatio(Double tbAvgHostSizeExpansionRatio) {
        this.tbAvgHostSizeExpansionRatio = tbAvgHostSizeExpansionRatio;
    }

    public Integer getCrossPageTbCount() {
        return crossPageTbCount;
    }

    public void setCrossPageTbCount(Integer crossPageTbCount) {
        this.crossPageTbCount = crossPageTbCount;
    }

    public Double getCrossPageTbCountPercent() {
        return crossPageTbCountPercent;
    }

    public void setCrossPageTbCountPercent(Double crossPageTbCountPercent) {
        this.crossPageTbCountPercent = crossPageTbCountPercent;
    }

    public Integer getDirectJumpCount() {
        return directJumpCount;
    }

    public void setDirectJumpCount(Integer directJumpCount) {
        this.directJumpCount = directJumpCount;
    }

    public Double getDirectJumpCountPercent() {
        return directJumpCountPercent;
    }

    public void setDirectJumpCountPercent(Double directJumpCountPercent) {
        this.directJumpCountPercent = directJumpCountPercent;
    }

    public Integer getDirectJumpCount2Jumps() {
        return directJumpCount2Jumps;
    }

    public void setDirectJumpCount2Jumps(Integer directJumpCount2Jumps) {
        this.directJumpCount2Jumps = directJumpCount2Jumps;
    }

    public Double getDirectJumpCount2JumpsPercent() {
        return directJumpCount2JumpsPercent;
    }

    public void setDirectJumpCount2JumpsPercent(Double directJumpCount2JumpsPercent) {
        this.directJumpCount2JumpsPercent = directJumpCount2JumpsPercent;
    }

    public Long getTbRunTimes() {
        return tbRunTimes;
    }

    public void setTbRunTimes(Long tbRunTimes) {
        this.tbRunTimes = tbRunTimes;
    }

    public Long getTbExitTimes() {
        return tbExitTimes;
    }

    public void setTbExitTimes(Long tbExitTimes) {
        this.tbExitTimes = tbExitTimes;
    }

    public Integer getTbHashBuckets1() {
        return tbHashBuckets1;
    }

    public void setTbHashBuckets1(Integer tbHashBuckets1) {
        this.tbHashBuckets1 = tbHashBuckets1;
    }

    public Integer getTbHashBuckets2() {
        return tbHashBuckets2;
    }

    public void setTbHashBuckets2(Integer tbHashBuckets2) {
        this.tbHashBuckets2 = tbHashBuckets2;
    }

    public Double getTbHashBucketsPercent() {
        return tbHashBucketsPercent;
    }

    public void setTbHashBucketsPercent(Double tbHashBucketsPercent) {
        this.tbHashBucketsPercent = tbHashBucketsPercent;
    }

    public Double getTbHashOccupancy() {
        return tbHashOccupancy;
    }

    public void setTbHashOccupancy(Double tbHashOccupancy) {
        this.tbHashOccupancy = tbHashOccupancy;
    }

    public Double getTbHashAvgChain() {
        return tbHashAvgChain;
    }

    public void setTbHashAvgChain(Double tbHashAvgChain) {
        this.tbHashAvgChain = tbHashAvgChain;
    }

}
