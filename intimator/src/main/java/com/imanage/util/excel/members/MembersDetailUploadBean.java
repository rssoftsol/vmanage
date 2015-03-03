package com.imanage.util.excel.members;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.imanage.util.Utility;

public class MembersDetailUploadBean {
	public String memberId;
	public String memberIdError;
	public String phonenumber;
	public String phonenumberError;
	public String name;
	public String nameError;
	public String expiryDate;
	public String expiryDateError;
	public boolean hasError;
	
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
			phonenumber = "Phone number cannot be empty";
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
	
	@Override
	public String toString() {
		return hasError+ " " + memberId+" "+phonenumber+" "+expiryDate+" "+name;
	}
	
	public static void main(String[] args) {
		MembersDetailUploadBean membersDetailUploadBean = new MembersDetailUploadBean();
		membersDetailUploadBean.expiryDate = "266/07/1987";
		membersDetailUploadBean.validDate();
		System.out.println(membersDetailUploadBean.expiryDateError);
	}
}
