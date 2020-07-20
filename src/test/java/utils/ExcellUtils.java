package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellUtils {

	static XSSFWorkbook workBook;
	static XSSFSheet sheet;

	public ExcellUtils(String excelPath, String sheetName) {
		try {

			workBook = new XSSFWorkbook(excelPath);
			sheet = workBook.getSheet(sheetName);


		} catch (Exception exp) {
			// TODO Auto-generated catch block
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
		}


	}


	public static int getRowCount() {
		int rowCount =0;

		rowCount= sheet.getPhysicalNumberOfRows();
		System.out.println("No of Rows "+rowCount);
		return rowCount;

	}
	public static int getColCount() {

		int colCount =0;
		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("No of Cols"+colCount);
		return colCount;

	}
	public static String getData(int rowNum, int colNum) {

		String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
				
		return cellData;


	}
	
	
//	public static String getUserData(int rowNum, int colNum) {
//
//		String userData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();	
//		System.out.println(userData);
//		return userData;
//			}


}
