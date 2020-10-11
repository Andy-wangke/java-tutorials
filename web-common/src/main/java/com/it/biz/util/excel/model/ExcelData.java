package com.it.biz.util.excel.model;


public class ExcelData {
    private String wookSheetName;
    private int recordNum;
    private ExcelHeader excelHeader;
    private ExcelBody excelBody;
    
    public String getWookSheetName() {
        return wookSheetName;
    }
    public void setWookSheetName(String wookSheetName) {
        this.wookSheetName = wookSheetName;
    }
    public ExcelHeader getExcelHeader() {
        return excelHeader;
    }
    public void setExcelHeader(ExcelHeader excelHeader) {
        this.excelHeader = excelHeader;
    }
    public ExcelBody getExcelBody() {
        return excelBody;
    }
    public void setExcelBody(ExcelBody excelBody) {
        this.excelBody = excelBody;
    }
    public int getRecordNum() {
        return recordNum;
    }
    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }
}
