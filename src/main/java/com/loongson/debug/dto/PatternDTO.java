package com.loongson.debug.dto;

public class PatternDTO {
    private String operator;
    private String pattern;
    private long sumir1All;
    private long sumir2All;
    private double percentageir1All;
    private double percentageir2All;
    private double percentageir1Operator;
    private double percentageir2Operator;

    public PatternDTO() {
    }

    public PatternDTO(String operator, String pattern, long sumir1All, long sumir2All,  double percentageir1All, double percentageir2All, double percentageir1Operator, double percentageir2Operator) {
        this.operator = operator;
        this.pattern = pattern;
        this.sumir1All = sumir1All;
        this.sumir2All = sumir2All;

        this.percentageir1All = percentageir1All;
        this.percentageir2All = percentageir2All;
        this.percentageir1Operator = percentageir1Operator;
        this.percentageir2Operator = percentageir2Operator;
    }

    @Override
    public String toString() {
        return "PatternDTO{" +
                "operator='" + operator + '\'' +
                ", pattern='" + pattern + '\'' +
                ", sumir1All=" + sumir1All +
                ", sumir2All=" + sumir2All +

                ", percentageir1All=" + percentageir1All +
                ", percentageir2All=" + percentageir2All +
                ", percentageir1Operator=" + percentageir1Operator +
                ", percentageir2Operator=" + percentageir2Operator +
                '}';
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public long getSumir1All() {
        return sumir1All;
    }

    public void setSumir1All(long sumir1All) {
        this.sumir1All = sumir1All;
    }

    public long getSumir2All() {
        return sumir2All;
    }

    public void setSumir2All(long sumir2All) {
        this.sumir2All = sumir2All;
    }


    public double getPercentageir1All() {
        return percentageir1All;
    }

    public void setPercentageir1All(double percentageir1All) {
        this.percentageir1All = percentageir1All;
    }

    public double getPercentageir2All() {
        return percentageir2All;
    }

    public void setPercentageir2All(double percentageir2All) {
        this.percentageir2All = percentageir2All;
    }

    public double getPercentageir1Operator() {
        return percentageir1Operator;
    }

    public void setPercentageir1Operator(double percentageir1Operator) {
        this.percentageir1Operator = percentageir1Operator;
    }

    public double getPercentageir2Operator() {
        return percentageir2Operator;
    }

    public void setPercentageir2Operator(double percentageir2Operator) {
        this.percentageir2Operator = percentageir2Operator;
    }
}
