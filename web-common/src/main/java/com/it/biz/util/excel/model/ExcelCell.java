package com.it.biz.util.excel.model;

public class ExcelCell {

    private String cellValue;
    private int cellLength = 1;

    public ExcelCell() {

    }

    public ExcelCell(String cellValue) {
        this.cellValue = cellValue;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public int getCellLength() {
        return cellLength;
    }

    public void setCellLength(int cellLength) {
        this.cellLength = cellLength;
    }
}
