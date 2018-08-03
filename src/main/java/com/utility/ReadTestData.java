package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestData {
	public ArrayList readExcelvalue(String name){
		
        ArrayList  col = new ArrayList();
    try {
 
        FileInputStream file = new FileInputStream(new File("C://Users//Pooja//Desktop//eBayTestData.xlsx"));
 
        //Get the workbook instance for XLS file 
        XSSFWorkbook workbook = new XSSFWorkbook(file);
 
        //Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheet(name);
 
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
 
            //display from the third row until 5th
            if(row.getRowNum()>1){
            {
 
 
 
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
 
                //Getting the cell contents
                Cell cell = cellIterator.next();
 
                switch(cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
 
                   /** case Cell.CELL_TYPE_BLANK:
                        System.out.println("BLANK");
                        break;
                        **/
                }
 
                //add the values of the cell to the Arraylist 
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
                {
                System.out.print(cell.getNumericCellValue());
                col.add(cell.getNumericCellValue());
                } 
                else if (cell.getCellType() == Cell.CELL_TYPE_STRING) 
                {
                System.out.print(cell.getRichStringCellValue());
                col.add(cell.getRichStringCellValue());
                } 
                else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) 
                {
                System.out.print(cell.getBooleanCellValue());
                col.add(cell.getBooleanCellValue());
                }
            }
 
            }
            }
            System.out.println("");
 
        }
        
 
        file.close();
       
      
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	return col;
    }


}
