package com.imanage.util;

public enum MemberModeEnum {
	ADD("ADD"),MODIFY("MODIFY"),BROWSE("BROWSE"),DELETE("DELETE");
	String code;
	MemberModeEnum(String code){
		this.code = code;
	}
	
	public static MemberModeEnum getMemberModeEnum(String code){
		MemberModeEnum memberModeEnum = null;
		for(MemberModeEnum memberModeEnumtmp : MemberModeEnum.values()){
			if(memberModeEnumtmp.code.equalsIgnoreCase(code)){
				memberModeEnum = memberModeEnumtmp;
			}
		}
		return memberModeEnum;
	}
}
