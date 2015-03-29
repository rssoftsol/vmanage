package com.imanage.util.excel;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.imanage.exception.ExcelException;

public abstract class ExcelProcessor<T> implements IExcelProcessor<T>{
	
	@Override
	public Vector<T> importExcelSheet(InputStream is) throws ExcelException{
	    Vector<T> cellVectorHolder = new Vector<T>();
	    try{
	        Workbook workBook = WorkbookFactory.create(is);
	        Sheet sheet = workBook.getSheetAt(0);
	        Iterator<Row> rowIter = sheet.rowIterator();
	        if(rowIter.hasNext()){
	        	Row headerRow = rowIter.next();
	        	if(!validExcelFormat(headerRow.cellIterator())){
	        		throw new ExcelException("Invalid file format");
	        	}
	        }
	        int count = 0;
	        while(rowIter.hasNext()){
	        	System.out.println(++count);
	        	
	        	Row row = rowIter.next();
	        	if(row == null || !isRowValid(row)) continue;
	            processExcelRow(cellVectorHolder, row.cellIterator());
	        }
	    }catch (Exception e){
	        System.out.println(e.getMessage());
	        throw new ExcelException("Invalid file format");
	    }
	    return cellVectorHolder;
	}
	
	private boolean isRowValid(Row row){
		Iterator cellIter = row.cellIterator();
		while(cellIter.hasNext()){
			HSSFCell cell = (HSSFCell) cellIter.next();
			if(cell!=null && !cell.toString().equalsIgnoreCase("")) return true;
		}
		return false;
	}
	@Override
	public void exportExcelSheet(Vector<Vector<XSSFCell>> data) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void processExcelRow(Vector<T> cellStoreVector, Iterator cellIter);
	public abstract void validateProcessedRow(T t);
	public abstract boolean validExcelFormat(Iterator cellIter);
}
