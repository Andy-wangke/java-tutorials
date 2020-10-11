package com.it.biz.util.excel.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExcelRow {
    private List<ExcelCell> cells;
    private int length;
    
    public ExcelRow(){
    }
    
    
    public ExcelRow(int length){
        this.length = length;
        this.cells = new LinkedList<ExcelCell>();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public Iterator getIterator(){
        
        if(cells == null){
            cells = new LinkedList<ExcelCell>();
        }

         return cells.iterator();
    }
    
    public void addCell(ExcelCell excelCell){
        if(cells == null){
            cells = new LinkedList<ExcelCell>();
        }
        cells.add(excelCell);
    }
    
    public void destroy(){
        if(cells != null){
            cells.clear();
            cells = null;
        }
    }
}
