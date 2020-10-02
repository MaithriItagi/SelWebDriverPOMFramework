package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static Workbook book;
	private static Sheet sheet;
	
	public static String TEST_DATA_SHEET_PATH=".src\\main\\java\\com\\qa\\hubspot\\data\\HubSpot_Testdata.xlsx";

	public static Object[][] getTestData(String sheetName) {
		Object data[][]= null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			
			book = WorkbookFactory.create(ip);
			sheet= book.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i = 1; i<sheet.getLastRowNum();i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j] = sheet.getRow(i).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Sheet not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
