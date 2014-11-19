package ru.kpfu.ivmit.ooaip.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class XlsTextProvider extends AbstractTextProvider {
	public static final String XLS_EXT = "xls";

	public XlsTextProvider(File inFile) {
		super(inFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeFile(String fileName, String source, String text) {
		try (FileInputStream file = new FileInputStream(getReadFile())) {
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			final int sheetsCount = workbook.getNumberOfSheets();

			for (int sheetIndex = 0; sheetIndex < sheetsCount; sheetIndex++) {
				HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							String sourceText = cell.getStringCellValue();
							String replacedText = sourceText.replaceAll(source,
									text);
							cell.setCellValue(replacedText);
						}
					}
				}
			}
			try (FileOutputStream outFile = new FileOutputStream(new File(
					fileName + "." + XLS_EXT))) {
				workbook.write(outFile);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
