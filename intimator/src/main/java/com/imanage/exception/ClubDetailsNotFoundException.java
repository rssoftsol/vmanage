package com.imanage.exception;

public class ClubDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	private final int smartphoneId;
	
	public ClubDetailsNotFoundException(int id) {
		smartphoneId = id;
	}
	
	public int getSmartphoneId() {
		return smartphoneId;
	}

}
