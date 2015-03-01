package com.imanage.util.excel.uploadexcel.impl;

import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import com.imanage.exception.ExcelException;
import com.imanage.util.excel.members.MembersDetailExcelReader;
import com.imanage.util.excel.members.MembersDetailUploadBean;
import com.imanage.util.excel.uploadexcel.AbstractUploadExcel;

public class UploadMembersExcelImpl extends AbstractUploadExcel<MembersDetailUploadBean> {
	
	private Set<String> memberIds = null;
	private String invalidMembersString  = "";
	private String validMembersString = "";
	
	public void setMemberDetails(Set<String> memberids) {
		this.memberIds = memberids;
	}

	@Override
	public boolean isExcel() {
		// TODO Auto-generated method stub
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType());
	}

	@Override
	public boolean allDataInExcelIsValid() {
		return invalidMembersString == null;
	}

	@Override
	public void processMyExcel() throws ExcelException {
		try {
			MembersDetailExcelReader membersDetailExcelReader = new MembersDetailExcelReader();
			membersDetailExcelReader.memberDetails = memberIds;
			Vector<MembersDetailUploadBean> excelData = membersDetailExcelReader.
					 importExcelSheet(file.getInputStream());
			afterProcessing(excelData);
		} catch (ExcelException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterProcessing(Vector<MembersDetailUploadBean> excelData) {
		for(MembersDetailUploadBean membersDetailUploadBean : excelData){
			if(membersDetailUploadBean.hasError){
				invalidMembersString = invalidMembersString + membersDetailUploadBean.memberId+"~"+
						membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
						membersDetailUploadBean.expiryDate+"~"+membersDetailUploadBean.getErrorString()+"!";
			}else{
				validMembersString = validMembersString + membersDetailUploadBean.memberId+"~"+
						membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
						membersDetailUploadBean.expiryDate+"!";
			}
		}
	}

	public String getInvalidMembersString() {
		return invalidMembersString;
	}

	public String getValidMembersString() {
		return validMembersString;
	}
	
	
	
}
