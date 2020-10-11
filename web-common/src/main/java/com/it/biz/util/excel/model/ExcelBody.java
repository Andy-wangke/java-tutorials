package com.it.biz.util.excel.model;

import java.util.List;

public class ExcelBody {

    private List<ExcelRow> rows;
    private int length;

    public List<ExcelRow> getRows() {
        return rows;
    }

    public void setRows(List<ExcelRow> rows) {
        this.rows = rows;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
