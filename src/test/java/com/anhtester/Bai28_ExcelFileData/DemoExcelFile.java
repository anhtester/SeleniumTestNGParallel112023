package com.anhtester.Bai28_ExcelFileData;

import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcelFile {
    @Test
    public void getDataFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        //Get simple data from Excel
        System.out.println(excelHelper.getCellData("EMAIL", 1));
        System.out.println(excelHelper.getCellData("PASSWORD", 1));
        System.out.println("***************************");
        //Get multiple data from Excel
        for (int i = 1; i <= 3; i++) {
            System.out.println(excelHelper.getCellData("EMAIL", i));
            System.out.println(excelHelper.getCellData("PASSWORD", i));
        }
        System.out.println("***************************");
        //Set data to Excel
        excelHelper.setCellData("Passed", "STATUS", 1);
        excelHelper.setCellData("Failed", "STATUS", 2);
    }
}