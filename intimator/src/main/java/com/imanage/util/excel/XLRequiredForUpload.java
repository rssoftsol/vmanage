package com.imanage.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.imanage.controllers.MembersDetailController;

public class XLRequiredForUpload extends AbstractView {
	

	
	public void downloadExcel(HttpServletRequest request,
            HttpServletResponse response) {
        // create some sample data
    	//ServletContext context = request.getServletContext();
        ClassLoader classLoader = MembersDetailController.class.getClassLoader();
		String path = classLoader.getResource("/resources").getPath();
		String fullPath = path+"/sampleexcel/mymembers.xls";
		File downloadFile = new File(fullPath);
        
        String mimeType = null;//context.getMimeType(fullPath);
            // set to binary type
        mimeType = "application/octet-stream";
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        try {
        	FileInputStream inputStream = new FileInputStream(downloadFile);
			OutputStream outStream = response.getOutputStream();
			final int BUFFER_SIZE = 4096;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
 
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			    outStream.write(buffer, 0, bytesRead);
			}
 
			inputStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		downloadExcel(request, response);
	}
}