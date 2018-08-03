package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.driver.DriverClass;


public class KeyActions extends DriverClass {

	private static final Logger log = Logger.getLogger(KeyActions.class);
	
	
	public KeyActions()
	{
		
	}
	
	public void click(By locator) throws Exception {
		log.info("Click on the the Element");
		try {
			System.out.println(locator);
			driver.findElement(locator).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendKeys(By locator, String strValue) throws Exception {
		log.info("Enter the text--" + strValue + "in te text box");
		try {
			driver.findElement(locator).sendKeys(strValue);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void waitUntilElementVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			log.debug("Sync up for the next element..." + locator);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			log.error("Element is not visible on the page", e);
		}
	}
	
	public boolean isDisplayed(By locator)
    {

	
        boolean status = false;
        try {
        	status = driver.findElement(locator).isDisplayed();
			log.debug("Verifying element displayed: " + status);
            
        } catch (Exception e) {
        }
        return status;

    }
	
	
	 public void waitUntilElementInvisiblity(By locator) {

		    try {
		      WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		      log.debug("Waiting for the invisibility of the element" );
		      wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		    } catch (Exception e) {
		      log.error("Element is not visible on the page", e);
		    }

		  }
	
	public void waitForElementInvisibility(By locator) {
	       try{
	    	boolean exit=false;
	    	do {
				if (isDisplayed(locator)) {
					Thread.sleep(10);
					log.info("waiting for the invisiblity of the element");
				}else{
					exit=true;
				}
			} while (exit==false);
	       }catch (Exception e) {
				e.printStackTrace();
			}
	    }
	
	public void Swipe(String direction, String speed) throws InterruptedException
	{
		LOGGER.info("Entered Swipe Loop");
		try{

			direction=direction.toLowerCase();
			org.openqa.selenium.Dimension size;
			int starty,startx,scrWidth,scrHeight,rpm;			
			size=driver.manage().window().getSize();
			scrWidth=size.getWidth();
			LOGGER.info("Screen width: "+scrWidth);
			scrHeight=size.getHeight();
			LOGGER.info("screen width "+scrHeight);
			startx=(int)(size.width*0.5);
			starty=(int)(size.height*0.5);
			LOGGER.info("start x "+startx);
			LOGGER.info("start y "+starty);
			LOGGER.info("right swipe starting point - "+startx);
			String sDir;
			sDir=direction.toLowerCase();
			speed=speed.toLowerCase();
			if (speed=="slow") {
				rpm=250;
			}else if(speed=="medium"){
				rpm=150;
			}else{
				rpm=50;
			}
			switch (sDir) {
			case("up"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx,starty+(scrHeight-rpm-(starty)),startx,starty-(scrHeight-rpm-(starty)),3000);
				break;
			case("down"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx,starty-(scrHeight-rpm-(starty)),startx,starty-(scrHeight-rpm-(starty)),3000);			
				break;
			case("right"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx+(scrWidth-50-(startx)),starty,startx-(scrWidth-rpm-(startx)),starty,2000);		
				break;
			case("left"):
				LOGGER.info("Swiping in "+direction+"  direction");
				((AndroidDriver) driver).swipe(startx-(scrWidth-50-(startx)),starty,startx+(scrWidth-rpm-(startx)),starty,3000);
			break;
			
			default:
				break;
			}
		}
			catch(Exception e){
				LOGGER.info("Exception block inside Swipe Page");
				e.printStackTrace();
			}
	}

	
	
	
	public void readExcelvadfgdflue(String name){
		
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
		            if(row.getRowNum()>0){
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
		    }

		
	
}
