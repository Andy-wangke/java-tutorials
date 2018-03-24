package com.it.poi.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * read the data in excel 2007 to json
 * 
 * @author kewan
 */
public class ReadExcelUtilsV2 {

    @SuppressWarnings("rawtypes")
    private static Map<String, List> sheetsData;

    private static Gson gson;

    private static String username = "ke.wang@hpe.com";
    private static String pwd = "Y1ZFNU9ETXdNREkzTkRBdA==";
    private static String fileName = "MDM%20updown%20stream%20systems.xlsx";
    private static Logger log = Logger.getLogger(ReadExcelUtilsV2.class);

    static {
        gson = new GsonBuilder().setDateFormat("yyyyMMdd").create();
    }

    public ReadExcelUtilsV2() {

    }

    public static List<Sheet> getRemoteExcelContents() {
        String myServer = "https://ent302.sharepoint.hpe.com/teams/mdm-it-share/";

        String urlString = myServer + "_vti_bin/ExcelRest.aspx/Shared%20Documents/BA%20Sharing/" + fileName + "/model?$format=workbook";

        InputStream is = null;
        // BufferedReader br = null;
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(AuthScope.ANY), new NTCredentials(username, CipherUtils.decodeTwiceToString(pwd), myServer, ""));
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        try {
            HttpGet httpget = new HttpGet(urlString);
            httpget.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            // httpget.setHeader("Transfer-Encoding", "chunked");
            // httpget.setHeader("Content-Encoding", "gzip");
            httpget.setHeader("Content-Disposition", " attachment; filename=" + fileName);
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpget.setHeader("Connection", "Keep-Alive");
            log.info("Executing request ---------------------->" + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                is = (InputStream) response.getEntity().getContent();
                // Workbook wb = new XSSFWorkbook(is);
                if (response.getStatusLine().getStatusCode() == 200) {
                    // file = saveResponseToFile(br);

                    return readSheet(fileName, is);
                } else {
                    log.error("have no file found");
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    log.error(e.getMessage());
                }

                if (is != null) {
                    is.close();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * read sheet where the excel name is filename
     * 
     * @param fileName
     * @return
     */
    private static List<Sheet> readSheet(String fileName, InputStream is) {
        boolean isExl2007 = false;
        Workbook wb = null;

        if (fileName.endsWith(".xlsx")) {
            isExl2007 = true;
        }
        try {
            if (isExl2007) {// 2007
                wb = new XSSFWorkbook(is);
            } else {// 2003
                wb = new HSSFWorkbook(is);
            }
            log.info("is.toString()--------->" + wb.getNumberOfSheets());
            // wb = WorkbookFactory.create(is);
            List<Sheet> sheets = new ArrayList<Sheet>();
            if (0 < wb.getNumberOfSheets()) {
                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    sheets.add(wb.getSheetAt(i));
                }
                return sheets;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, List> getSheetData() {
        sheetsData = new HashMap<String, List>();

        List<Map<String, String>> rowForSheet = null;
        Map<String, String> sheetDt = null;

        Row title = null;
        List<Sheet> sheets = null;
        if (null != getRemoteExcelContents()) {
            sheets = getRemoteExcelContents();

            if (null != sheets && 0 < sheets.size()) {
                for (int i = 0; i < sheets.size(); i++) {
                    Sheet sheet = sheets.get(i);
                    rowForSheet = new ArrayList<Map<String, String>>();
                    for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {// row

                        Row row = sheet.getRow(j);
                        if (j == 0) { // the first row is title
                            title = row;
                            continue;
                        }

                        if (null != row && 0 < title.getPhysicalNumberOfCells()) {
                            sheetDt = new HashMap<String, String>();
                            for (int k = 0; k < title.getPhysicalNumberOfCells(); k++) {// cell
                                sheetDt.put(getCellValue(title.getCell(k)), getCellValue(row.getCell(k)));
                            }
                        }
                        rowForSheet.add(sheetDt);
                    }
                    title = null;
                    sheetsData.put(sheet.getSheetName(), rowForSheet);
                }
            }
            return sheetsData;
        }

        return null;
    }

    public static String readTexttoJson() {

        if (null != getSheetData()) {
            return gson.toJson(getSheetData());
        }
        return null;
    }

    /**
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) { // 日期类型
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    value = sdf.format(date);
                } else {
                    Integer data = (int) cell.getNumericCellValue();
                    value = data.toString();
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                Boolean data = cell.getBooleanCellValue();
                value = data.toString();
                break;
            case Cell.CELL_TYPE_ERROR:
                // System.out.println("单元格内容出现错误");
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值非法,就将其装换为对应的字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                // value = "_BLANK";
                value = "";
                // System.out.println("单元格内容 为空值 ");
                break;
            default:
                value = cell.getStringCellValue().toString();
                break;
            }
        } else if (cell == null || "".equals(cell)) {
            // value = "undefined";
            value = "";
        }
        return value;
    }

    public static void exportJson() throws Exception {
        String str = readTexttoJson();
        File file = new File("E:\\MDM.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        try {
            bw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            exportJson();
            // getRemoteExcelContents();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
