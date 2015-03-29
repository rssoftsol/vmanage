package com.imanage.util.excel.uploadexcel.impl;

import java.util.Set;
import java.util.Vector;

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
		return "application/csv".equals(file.getContentType()) || "application/vnd.ms-excel".equals(file.getContentType());
	}

	@Override
	public boolean allDataInExcelIsValid() {
		return "".equalsIgnoreCase(invalidMembersString);
	}

	@Override
	public void processMyExcel() throws Exception {
		MembersDetailExcelReader membersDetailExcelReader = new MembersDetailExcelReader();
		membersDetailExcelReader.memberDetails = memberIds;
		membersDetailExcelReader.dateFormat = dateFormat;
		Vector<MembersDetailUploadBean> excelData = membersDetailExcelReader.
				 importExcelSheet(file.getInputStream());
		//put logger here
		System.out.println("excelData:"+excelData);
		afterProcessing(excelData);
	}
	
	@Override
	public void afterProcessing(Vector<MembersDetailUploadBean> excelData) {
		for(MembersDetailUploadBean membersDetailUploadBean : excelData){
			if(membersDetailUploadBean.hasError){
				invalidMembersString = invalidMembersString + membersDetailUploadBean.memberId+"~"+
						membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
						membersDetailUploadBean.getExpiryDate()+"~"+membersDetailUploadBean.remarks+"~"
						+membersDetailUploadBean.getErrorString()+"!";
			}else{
				validMembersString = validMembersString + membersDetailUploadBean.memberId+"~"+
						membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
						membersDetailUploadBean.getExpiryDate()+"~"+
						membersDetailUploadBean.remarks+"!";
			}
		}
		System.out.println("invalidMembersString:"+invalidMembersString);
		System.out.println("validMembersString:"+validMembersString);
		//put logger here
	}

	public String getInvalidMembersString() {
		return invalidMembersString;
	}

	public String getValidMembersString() {
		return validMembersString;
	}
}
