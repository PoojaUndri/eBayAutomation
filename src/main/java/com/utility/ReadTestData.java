package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestData {
	
	    public FileInputStream fis = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	    public String colVal_row0;
	    public String colVal_row1;
	    
	    
	    Map<String, String> data = null;
	 
	   
	    public Map<String, String> getCellData(String filePath,String sheetName) throws IOException
	    {
	    	fis = new FileInputStream(filePath);
	    	workbook = new XSSFWorkbook(fis);
	    	data=new HashMap<String, String>();
	        try
	        {
	            sheet = workbook.getSheet(sheetName);
	            row = sheet.getRow(0);
	            for(int i = 0; i < row.getLastCellNum(); i++)
	            {
	            	DataFormatter formatter = new DataFormatter();
	            	colVal_row0=formatter.formatCellValue((row.getCell(i)));
	                    
	            	 
	            row = sheet.getRow(1);
	            cell = row.getCell(i);
	 
	            if(cell.getCellType() == Cell.CELL_TYPE_STRING)
	               colVal_row1= cell.getStringCellValue();
	            else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
	            {
	            	 colVal_row1 = String.valueOf(cell.getNumericCellValue());
	              
	            }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
	            	colVal_row1= "";
	            else
	                colVal_row1=String.valueOf(cell.getBooleanCellValue());
	            row = sheet.getRow(0);

	            data.put(colVal_row0, colVal_row1);
	            }
	            
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            
	        }
			return data;
			
	    }
	    
	    
	}
	

