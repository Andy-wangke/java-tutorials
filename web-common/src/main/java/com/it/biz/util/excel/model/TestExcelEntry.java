package com.it.biz.util.excel.model;

import com.it.biz.util.excel.model.annotation.ExcelElement;

public class TestExcelEntry {

    @ExcelElement(order = 0, alias = "column1")
    private String column1;
    @ExcelElement(order = 1, alias = "column2")
    private String column2;
    @ExcelElement(order = 2, alias = "column3")
    private String column3;

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

}
