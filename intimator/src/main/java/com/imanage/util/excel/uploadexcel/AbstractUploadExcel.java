package com.imanage.util.excel.uploadexcel;

import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.imanage.exception.ExcelException;

public abstract class AbstractUploadExcel<T> implements IUploadExcel {
	protected MultipartFile file;
	@Override
	public void upload(MultipartFile file) throws ExcelException {
		// TODO Auto-generated method stub
		this.file = file;
		if(isExcel()){
			processMyExcel();
		}else{
			throw new ExcelException("Invalid file format");
		}
	}
	
	public abstract boolean isExcel();
	
	public abstract boolean allDataInExcelIsValid();
	
	public abstract void processMyExcel()  throws ExcelException;
	
	public abstract void afterProcessing(Vector<T> excelData)  throws ExcelException;
}
