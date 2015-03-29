package com.imanage.util.excel.uploadexcel;

import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.imanage.exception.ExcelException;

public abstract class AbstractUploadExcel<T> implements IUploadExcel {
	protected MultipartFile file;
	public String dateFormat = "dd/mm/yyyy";
	@Override
	public void upload(MultipartFile file) throws Exception {
		upload(file, null);
	}
	
	@Override
	public void upload(MultipartFile file, String dateFormat) throws Exception {
		// TODO Auto-generated method stub
		this.file = file;
		this.dateFormat = dateFormat;
		if(isExcel()){
			processMyExcel();
		}else{
			throw new ExcelException("Invalid file format");
		}
	}
	
	public abstract boolean isExcel();
	
	public abstract boolean allDataInExcelIsValid();
	
	public abstract void processMyExcel()  throws Exception;
	
	public abstract void afterProcessing(Vector<T> excelData)  throws Exception;
}
