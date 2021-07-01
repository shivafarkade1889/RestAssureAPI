package data.excel;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	public ArrayList<String> getData(String testCaseName, String sheetName) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		String fileName = System.getProperty("user.dir") + "\\resoruces\\Data.xlsx";
		System.out.println(fileName);
		FileInputStream file = new FileInputStream(fileName);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		int count = workbook.getNumberOfSheets();
		for (int i = 0; i < count; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {

				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equals("TestCases")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								data.add(c.getStringCellValue());
							} else {
								data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}

					}

				}

			}
		}
		return data;

	}

}
