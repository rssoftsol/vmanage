package com.imanage.util.excel.uploadexcel;

import org.springframework.web.multipart.MultipartFile;

import com.imanage.exception.ExcelException;

public interface IUploadExcel {
	public void upload(MultipartFile file) throws ExcelException;
}
