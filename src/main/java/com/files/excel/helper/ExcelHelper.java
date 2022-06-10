package com.files.excel.helper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.files.excel.entity.ExcelModel;

public class ExcelHelper {
	public static String TYPE = "excelUpload";
	  static String[] HEADERs = { "Id", "Nombre", "Apellido", "Estado" };
	  static String SHEET = "ExcelModel";
	  public static boolean hasExcelFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  public static List<ExcelModel> excelToTutorials(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);
	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();
	      List<ExcelModel> excelmodels = new ArrayList<ExcelModel>();
	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();
	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }
	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        ExcelModel excelmodel = new ExcelModel(rowNumber, SHEET, SHEET, false);
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
	          switch (cellIdx) {
	          case 0:
	            excelmodel.setId((long) currentCell.getNumericCellValue());
	            break;
	          case 1:
	        	  excelmodel.setNombre(currentCell.getStringCellValue());
	            break;
	          case 2:
	        	  excelmodel.setApellido(currentCell.getStringCellValue());
	            break;
	          case 3:
	        	  excelmodel.setEstado(currentCell.getBooleanCellValue());
	            break;
	          default:
	            break;
	          }
	          cellIdx++;
	        }
	        excelmodels.add(excelmodel);
	      }
	      workbook.close();
	      return excelmodels;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }

}
