package com.it.biz.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.it.biz.util.excel.model.ExcelBody;
import com.it.biz.util.excel.model.ExcelCell;
import com.it.biz.util.excel.model.ExcelData;
import com.it.biz.util.excel.model.ExcelHeader;
import com.it.biz.util.excel.model.ExcelRow;
import com.it.biz.util.excel.model.TestExcelEntry;
import com.it.biz.util.excel.model.annotation.ExcelElement;

public class ExcelUtil {

    private static final Logger s_logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * import excel from stream
     * 
     * @param in
     * @param fileName
     * @return
     */
    public static ExcelData importExcel(InputStream is, String fileName) {
        // TODO
        return null;

    }

    /**
     * export data from object
     * 
     * @param data
     * @return
     */
    public static byte[] exportExcel(ExcelData data) {
        Workbook workBook = new HSSFWorkbook();
        Sheet sheet = workBook.createSheet(data.getWookSheetName());
        Row headerRow = sheet.createRow(0);

        Font headerFont = workBook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workBook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        try {
            // assemble header row
            Iterator<ExcelCell> headerCellIterator = data.getExcelHeader().getHeaderRow().getIterator();
            int headRowCount = 0;
            while (headerCellIterator.hasNext()) {
                ExcelCell excelCell = headerCellIterator.next();
                Cell cell = headerRow.createCell(headRowCount);
                cell.setCellValue(excelCell.getCellValue());
                cell.setCellStyle(headerCellStyle);
                headRowCount++;
            }
            // assemble body row
            int bodyRowCount = 1;
            Iterator<ExcelRow> bodyRowIterator = data.getExcelBody().getRows().iterator();
            while (bodyRowIterator.hasNext()) {
                ExcelRow excelRow = bodyRowIterator.next();
                Row bodyRow = sheet.createRow(bodyRowCount);

                int bodyRowCelCount = 0;
                Iterator<ExcelCell> bodyCellIterator = excelRow.getIterator();
                while (bodyCellIterator.hasNext()) {
                    ExcelCell excelCell = bodyCellIterator.next();
                    Cell bodyCell = bodyRow.createCell(bodyRowCelCount);
                    bodyCell.setCellValue(excelCell.getCellValue());
                    bodyRowCelCount++;
                    excelCell = null;
                }
                bodyRowCount++;
                excelRow.destroy();
                excelRow = null;
            }

            for (int i = 0; i < headRowCount; i++) {
                sheet.autoSizeColumn(i);
            }
        } catch (Exception e) {
            s_logger.error("ExcelUtil export Excel Error : " + e.getMessage());
        }
        // write workBook to a file
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        byte[] byteContent = null;
        try {
            workBook.write(fileOut);
            byteContent = fileOut.toByteArray();
        } catch (IOException e) {
            s_logger.error("ExcelUtils exportExcel Error : " + e.getMessage());
        } finally {
            try {
                fileOut.flush();
                fileOut.close();
            } catch (IOException e) {
                s_logger.error("ExcelUtils exportExcel outstream close Error : " + e.getMessage());
            }

            try {
                workBook.close();
            } catch (IOException e) {
                s_logger.error("ExcelUtils exportExcel workbook close Error : " + e.getMessage());
            }
        }
        return byteContent;

    }

    public static <S> ExcelData createExcelData(List<S> objList, String sheetName) throws IllegalArgumentException, IllegalAccessException {
        ExcelData excelData = new ExcelData();

        if (objList != null && !objList.isEmpty()) {
            Class clz = objList.get(0).getClass();

            Field[] fields = clz.getDeclaredFields();
            Field[] selectedFields = new Field[fields.length];
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(ExcelElement.class)) {
                    ExcelElement annotationType = field.getAnnotation(ExcelElement.class);
                    selectedFields[annotationType.order()] = field;
                }
            }

            ExcelRow headerRow = new ExcelRow();
            for (Field field : selectedFields) {
                if (field == null)
                    continue;

                ExcelCell cell = new ExcelCell();
                cell.setCellValue(field.getAnnotation(ExcelElement.class).alias());
                headerRow.addCell(cell);
            }
            ExcelHeader excelHeader = new ExcelHeader();
            excelHeader.setHeaderRow(headerRow);

            List<ExcelRow> bodyRows = new ArrayList<ExcelRow>();

            for (S obj : objList) {
                ExcelRow row = new ExcelRow();
                for (Field field : selectedFields) {
                    if (field == null) {
                        continue;
                    }
                    row.addCell(new ExcelCell((String) field.get(obj)));
                }
                bodyRows.add(row);
                obj = null;
            }
            if (objList != null) {
                objList.clear();
                objList = null;
            }
            ExcelBody excelBody = new ExcelBody();
            excelBody.setRows(bodyRows);
            excelBody.setLength(bodyRows.size());

            excelData.setExcelHeader(excelHeader);
            excelData.setExcelBody(excelBody);
            excelData.setRecordNum(excelBody.getLength());
            excelData.setWookSheetName(sheetName);
        }
        return excelData;
    }

    public static ExcelHeader createHeader(List<String> headerList) {
        ExcelHeader excelHeader = new ExcelHeader();
        ExcelRow excelRow = new ExcelRow(headerList.size());
        for (String header : headerList) {
            ExcelCell cell = new ExcelCell();
            cell.setCellValue(header);
            excelRow.addCell(cell);
        }
        return excelHeader;
    }

    public static void main(String[] args) {
        List<TestExcelEntry> rowList = new ArrayList<>();
        TestExcelEntry row1 = new TestExcelEntry();
        row1.setColumn1("cell11");
        row1.setColumn2("cell12");
        row1.setColumn3("cell13");
        rowList.add(row1);

        TestExcelEntry row2 = new TestExcelEntry();
        row2.setColumn1("cell21");
        row2.setColumn2("cell22");
        row2.setColumn3("cell23");
        rowList.add(row2);

        TestExcelEntry row3 = new TestExcelEntry();
        row3.setColumn1("cell31");
        row3.setColumn2("cell32");
        row3.setColumn3("cell33");
        rowList.add(row3);
        
        TestExcelEntry row4 = new TestExcelEntry();
        row4.setColumn1("cell41");
        row4.setColumn2("cell42");
        row4.setColumn3("cell43");
        rowList.add(row4);

        byte[] byteContent = null;
        try {
            ExcelData excelData = ExcelUtil.createExcelData(rowList, "TestExcel");
            byteContent = ExcelUtil.exportExcel(excelData);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (byteContent != null) {
            try {
                FileUtil.export("OutputExcel.xls", byteContent);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            FileOutputStream output = null;
//            try {
//                File file = new File("OutputExcel.xls");
//                output = new FileOutputStream(file);
//                output.write(byteContent);
//                output.flush();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } finally {
//                if (null != output) {
//                    try {
//                        output.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            System.out.println("export success!");
        }

    }
}
