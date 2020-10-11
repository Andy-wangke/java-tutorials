
package com.it.biz.util.excel;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static final Logger s_logger = LoggerFactory.getLogger(FileUtil.class);

    public static void export(String pathName, byte[] fileContent) throws FileNotFoundException {

        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(pathName));
            outputStream.write(fileContent);
            outputStream.close();
        } catch (FileNotFoundException e) {
            s_logger.error("File Not Found: " + e.getMessage());
        } catch (IOException e) {
            s_logger.error("IOException: " + e.getMessage());
        }
    }

    public static void export(String url, String fileName) {
        //TODO
    }
}
