package com.loongson.debug.dto;

public class PatternFilter {
    private String operator;
    private String pattern;

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

    public PatternFilter() {
    }

    public PatternFilter(String operator, String pattern) {
        this.operator = operator;
        this.pattern = pattern;

    }

    @Override
    public String toString() {
        return "PatternFilter{" +
                "operator='" + operator + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
