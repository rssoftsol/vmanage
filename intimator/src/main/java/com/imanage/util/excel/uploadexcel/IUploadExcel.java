package com.imanage.util.excel.uploadexcel;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadExcel {
	public void upload(MultipartFile file) throws Exception;
	public void upload(MultipartFile file, String dateFormat) throws Exception;
}
