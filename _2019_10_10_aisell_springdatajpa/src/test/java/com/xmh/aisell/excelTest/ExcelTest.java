package com.xmh.aisell.excelTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 */
public class ExcelTest {


/*    @Test
    public void testOut() throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("九九乘法表");
        for (int i = 1; i <=9 ; i++) {
            Row row = sheet.createRow(i - 1);
            for (int j = 1; j <=i ; j++) {
                Cell cell = row.createCell(j - 1);
                cell.setCellValue(i+"X"+j+"="+(i*j));
            }
        }
        FileOutputStream out = new FileOutputStream("九九乘法表.xlsx");
        workbook.write(out);
        out.close();
    }


    @Test
    public void testInput() throws Exception {
        File file = new File("九九乘法表.xlsx");
        FileInputStream fis = new FileInputStream(file);
        //1.读取一个Excel文件(内存中)
        Workbook wb = new XSSFWorkbook(fis);
        //2.拿到第个sheet表
        Sheet sheet = wb.getSheetAt(0);
        //3.拿到wb中的行(不要拿头部)
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //4.拿到每一列(格子)
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                //只能获取字符串不是字符串需要另行判断cell.getType
                System.out.print(cell.getStringCellValue()+" ");
            }
            System.out.println();
        }

    }*/


}
