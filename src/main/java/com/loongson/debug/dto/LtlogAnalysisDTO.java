package com.loongson.debug.dto;

import com.loongson.debug.entity.LtLog;
import com.loongson.debug.entity.LtlogAnalysis;

public class LtlogAnalysisDTO {
    private Integer ltid;
    private Long genCodeSize1;
    private Long genCodeSize2;
    private Integer tbCount;
    private Integer tbAvgTargetSize;
    private Integer tbAvgTargetSizeMax;
    private Integer tbAvgHostSize;
    private Double tbAvgHostSizeExpansionRatio;
    private Integer crossPageTbCount;
    private Double crossPageTbCountPercent;
    private Integer directJumpCount;
    private Double directJumpCountPercent;
    private Integer directJumpCount2Jumps;
    private Double directJumpCount2JumpsPercent;
    private Long tbRunTimes;
    private Long tbExitTimes;
    private Integer tbHashBuckets1;
    private Integer tbHashBuckets2;
    private Double tbHashBucketsPercent;
    private Double tbHashOccupancy;
    private Double tbHashAvgChain;
    private Boolean profiled;
    private LtLog ltlog;

    public LtlogAnalysisDTO() {
    }

    public LtlogAnalysisDTO(LtlogAnalysis ltlogAnalysis, LtLog ltLog) {
        this.ltid = ltlogAnalysis.getLtid();
        this.genCodeSize1 = ltlogAnalysis.getGenCodeSize1();
        this.genCodeSize2 = ltlogAnalysis.getGenCodeSize2();
        this.tbCount = ltlogAnalysis.getTbCount();
        this.tbAvgTargetSize = ltlogAnalysis.getTbAvgTargetSize();
        this.tbAvgTargetSizeMax = ltlogAnalysis.getTbAvgTargetSizeMax();
        this.tbAvgHostSize = ltlogAnalysis.getTbAvgHostSize();
        this.tbAvgHostSizeExpansionRatio = ltlogAnalysis.getTbAvgHostSizeExpansionRatio();
        this.crossPageTbCount = ltlogAnalysis.getCrossPageTbCount();
        this.crossPageTbCountPercent = ltlogAnalysis.getCrossPageTbCountPercent();
        this.directJumpCount = ltlogAnalysis.getDirectJumpCount();
        this.directJumpCountPercent = ltlogAnalysis.getDirectJumpCountPercent();
        this.directJumpCount2Jumps = ltlogAnalysis.getDirectJumpCount2Jumps();
        this.directJumpCount2JumpsPercent = ltlogAnalysis.getDirectJumpCount2JumpsPercent();
        this.tbRunTimes = ltlogAnalysis.getTbRunTimes();
        this.tbExitTimes = ltlogAnalysis.getTbExitTimes();
        this.tbHashBuckets1 = ltlogAnalysis.getTbHashBuckets1();
        this.tbHashBuckets2 = ltlogAnalysis.getTbHashBuckets2();
        this.tbHashBucketsPercent = ltlogAnalysis.getTbHashBucketsPercent();
        this.tbHashOccupancy = ltlogAnalysis.getTbHashOccupancy();
        this.tbHashAvgChain = ltlogAnalysis.getTbHashAvgChain();
        this.profiled = ltlogAnalysis.getProfiled();
        this.ltlog = ltLog;
    }

    @Override
    public String toString() {
        return "LtlogAnalysisDTO{" +
                "ltid=" + ltid +
                ", genCodeSize1=" + genCodeSize1 +
                ", genCodeSize2=" + genCodeSize2 +
                ", tbCount=" + tbCount +
                ", tbAvgTargetSize=" + tbAvgTargetSize +
                ", tbAvgTargetSizeMax=" + tbAvgTargetSizeMax +
                ", tbAvgHostSize=" + tbAvgHostSize +
                ", tbAvgHostSizeExpansionRatio=" + tbAvgHostSizeExpansionRatio +
                ", crossPageTbCount=" + crossPageTbCount +
                ", crossPageTbCountPercent=" + crossPageTbCountPercent +
                ", directJumpCount=" + directJumpCount +
                ", directJumpCountPercent=" + directJumpCountPercent +
                ", directJumpCount2Jumps=" + directJumpCount2Jumps +
                ", directJumpCount2JumpsPercent=" + directJumpCount2JumpsPercent +
                ", tbRunTimes=" + tbRunTimes +
                ", tbExitTimes=" + tbExitTimes +
                ", tbHashBuckets1=" + tbHashBuckets1 +
                ", tbHashBuckets2=" + tbHashBuckets2 +
                ", tbHashBucketsPercent=" + tbHashBucketsPercent +
                ", tbHashOccupancy=" + tbHashOccupancy +
                ", tbHashAvgChain=" + tbHashAvgChain +
                ", profiled=" + profiled +
                ", ltLog=" + ltlog +
                '}';
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

    public Boolean getProfiled() {
        return profiled;
    }

    public void setProfiled(Boolean profiled) {
        this.profiled = profiled;
    }

    public LtLog getLtlog() {
        return ltlog;
    }

    public void setLtlog(LtLog ltlog) {
        this.ltlog = ltlog;
    }

    public LtlogAnalysisDTO(Integer ltid, Long genCodeSize1, Long genCodeSize2, Integer tbCount, Integer tbAvgTargetSize, Integer tbAvgTargetSizeMax, Integer tbAvgHostSize, Double tbAvgHostSizeExpansionRatio, Integer crossPageTbCount, Double crossPageTbCountPercent, Integer directJumpCount, Double directJumpCountPercent, Integer directJumpCount2Jumps, Double directJumpCount2JumpsPercent, Long tbRunTimes, Long tbExitTimes, Integer tbHashBuckets1, Integer tbHashBuckets2, Double tbHashBucketsPercent, Double tbHashOccupancy, Double tbHashAvgChain, Boolean profiled, LtLog ltLog) {
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
        this.profiled = profiled;
        this.ltlog = ltLog;
    }
}
