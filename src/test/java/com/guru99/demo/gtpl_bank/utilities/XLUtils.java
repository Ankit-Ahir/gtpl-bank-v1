package com.guru99.demo.gtpl_bank.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fileInputStream;
	public static FileOutputStream fileOutputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fileInputStream=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(xlsheet);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return rowcount;
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fileInputStream=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fileInputStream=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		workbook.close();
		fileInputStream.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fileInputStream=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fileOutputStream=new FileOutputStream(xlfile);
		workbook.write(fileOutputStream);		
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	
}
