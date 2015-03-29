package com.imanage.util.excel.members;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.imanage.util.Utility;

public class MembersDetailUploadBean {
	public String memberId;
	public String memberIdError;
	public String phonenumber;
	public String phonenumberError;
	public String name;
	public String nameError;
	private String expiryDate;
	public String expiryDateError;
	public String remarks = "-";
	public boolean hasError;
	
	public String getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(String expiryDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat requiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		inputFormat.setLenient(false);
        try {
        	expiryDate = requiredFormat.format(new Date(inputFormat.parse(expiryDate).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.expiryDate = expiryDate;
	}
	
	public void setExpiryDate(String expiryDate, String inputFormat) {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
		SimpleDateFormat requiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		inputDateFormat.setLenient(false);
        try {
        	expiryDate = requiredFormat.format(new Date(inputDateFormat.parse(expiryDate).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.expiryDate = expiryDate;
	}
	
	public String getErrorString(){
		String error = "";
		String seperator = "";
		if(memberIdError!=null){
			error = error + memberIdError; 
			seperator = ", ";
		}
		if(phonenumberError!=null){
			error = error + seperator + phonenumberError;
			seperator = ", ";
		}
		if(nameError!=null){
			error = error + seperator + nameError;
			seperator = ", ";
		}
		if(expiryDateError!=null){
			error = error + seperator + expiryDateError;
		}
		return error;
	}
	public boolean validMemberId(Set<String> memberIds){
		if(memberId == null || "".equalsIgnoreCase(memberId)){
			memberIdError = "Member ID cannot be empty";
			hasError = true;
			return false;
		}
		if(memberIds.contains(memberId)){
			memberIdError = "Member ID Already Exist";
			hasError = true;
			return false;
		}
		return true;
	}
	
	public boolean validPhonenumber(){
		if(phonenumber == null || "".equalsIgnoreCase(phonenumber)){
			phonenumberError = "Phone number cannot be empty";
			hasError = true;
			return false;
		}
		
		if(phonenumber.length() != 10 || !Utility.isNumeric(phonenumber)){
			phonenumberError = "Invalid phone number";
			hasError = true;
			return false;
		}
		
		return true;	
	}
	
	public boolean validName(){
		if(name == null || "".equalsIgnoreCase(name)){
			nameError = "Name cannot be empty";
			hasError = true;
			return false;
		}
		return true;
	}
	
	public boolean validDate(){
		if(expiryDate == null){
			expiryDateError = "ExpiryDate cannot be empty";
			hasError = true;
			return false;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);
            new java.sql.Date(format.parse(expiryDate).getTime());
        } catch(ParseException e) {
        	expiryDateError = "Invalid Date";
        	hasError = true;
        	return false;
        }
		return true;
	}
	
	public boolean validRemarks(){
		if(remarks.length()>160){
			remarks = remarks.substring(0, 160);
		}
		return true;
	}
	
	@Override
	public String toString() {
		return hasError+ " " + memberId+" "+phonenumber+" "+expiryDate+" "+name+" "+remarks;
	}
	
	public static void main(String[] args) {
		/*MembersDetailUploadBean membersDetailUploadBean = new MembersDetailUploadBean();
		membersDetailUploadBean.expiryDate = "266/07/1987";
		membersDetailUploadBean.validDate();*/
		String str = "string"; 
		System.out.println(str.substring(0, 6));
	}
}
